package org.example.diameter;

import org.example.diameter.packet.factory.DiameterPacketDecodeGxFactory;
import org.example.diameter.packet.factory.DiameterPacketFactory;
import org.example.diameter.packet.DiameterPacket;
import org.example.diameter.packet.DiameterPacketHeader;
import org.example.diameter.packet.factory.DiameterPacketFactoryImpl;
import org.example.diameter.properties.DiameterServerProperties;
import org.example.diameter.service.DiameterPacketHandlerService;
import org.example.diameter.utils.ReadBytesUtils;
import org.example.diameter.utils.ReadDiameterHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
@Component
public class DiameterServer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DiameterServer.class);

    public static byte DIAMETER_VERSION = 0x01;
    private final AtomicInteger packetCounter = new AtomicInteger(0);
    private final String ip;
    private final int port;
    private final DiameterPacketFactory diameterPacketFactory;
    private final DiameterServerProperties properties;
    private final DiameterPacketHandlerService diameterPacketHandlerService;
    private Thread thread;


    public DiameterServer(DiameterPacketHandlerService diameterPacketHandlerService,
                          DiameterPacketFactory diameterPacketFactory, DiameterServerProperties properties) {
        this.properties = properties;
        this.ip = "localhost";
        this.port = 5858;
        this.diameterPacketFactory = diameterPacketFactory;
        this.diameterPacketHandlerService = diameterPacketHandlerService;
    }

    @Override
    public void run(String... args) {
        thread = new Thread(this::startServer);
        thread.start();
    }

    public void stopServer(){
        thread.interrupt();
    }

    public void startServer() {
        try {
            logger.info("starting server ip {} port {}",this.ip,this.port);
            Selector selector = Selector.open();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(this.ip, this.port));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("started server ip {} port {}",this.ip,this.port);

            while (!Thread.currentThread().isInterrupted()) {
                logger.info("Listening for incomming events");
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        register(selector, serverSocket);
                    }
                    if (key.isReadable()) {
                        readSocket(key);
                    }
                    iter.remove();
                }
            }
            logger.info("stop reading socket...closing socket");
            serverSocket.close();
        }catch (IOException ioException) {
            Thread.currentThread().interrupt();
            logger.info("Exception {}",ioException);
        }


    }

    private void readSocket(SelectionKey key) throws  IOException{
        SocketChannel client = (SocketChannel) key.channel();
        //only allocated once for each socket channel
        ByteBuffer buffer = key.attachment() != null ? (ByteBuffer) key.attachment() : ByteBuffer.allocate(2048);
        List<DiameterPacket> packetList = new ArrayList<>();
        int numBytsRead;
        try{
            logger.info("Reading from socket {}",client.getRemoteAddress().toString());
            while ((numBytsRead = client.read(buffer)) > 0) {
                //logger.info("Buffer before flip p {} l {} -in bytes {}",buffer.position(),buffer.limit(),numBytsRead);
                buffer.flip();
                //logger.info("Buffer after flip {} {}",buffer.position(),buffer.limit());
                packetList.addAll(readRawFromBuffer(buffer));

                //There is a partial packet still remaining in the buffer...remember this for next read iteration
                if (buffer.hasRemaining()) {
                    //create a tmp buffer to store the remaining bytes belonging to the partial packet
                    // and copy to the client buffer for next read
                    byte[] tmp = new byte[buffer.remaining()];
                    System.arraycopy(buffer.array(),buffer.position(),tmp,0,buffer.remaining());
                    buffer.clear();
                    buffer.put(tmp);
                    //logger.info("partial bytes added to byffer {} {}",buffer.position(),buffer.limit());
                } else {
                    buffer.clear();
                }
                //attach the buffer to the client socket
                key.attach(buffer);
            }
            logger.info("Number of read packets {}",this.packetCounter.get());
            if (numBytsRead < 0) {
                //close connection
                throw new IOException("Client disconnected");
            }
        }catch (IOException ioException) {
            logger.error("Closing client connection {} exception",client.getRemoteAddress().toString(),ioException);
            key.attach(null);
            key.cancel();
            client.close();
        }
        //submit packets to packet handler
        packetList.forEach( packet -> {
                    DiameterReqContext reqContext = ModifiableDiameterReqContext.create()
                            .setRequest(packet)
                            .setReceivedTime(System.currentTimeMillis())
                            .setSocketChannel(client);
                    this.diameterPacketHandlerService.handlePacket(reqContext);
                });
    }

    private List<DiameterPacket> readRawFromBuffer(ByteBuffer buffer) throws IOException {
        List<DiameterPacket> diameterPacketList = new ArrayList<>();
        byte[] packetLength = new byte[3];
        int originalPos=0;

        while (buffer.remaining()>20) {
            // we have at least a full header to read
            originalPos = buffer.position();
            byte version = buffer.get();
            if(version!=DIAMETER_VERSION) {
                throw new IOException("Packets out of order");
            }

            buffer.get(packetLength, 0, 3);
            //create the byte array to store the packet
            int len = ReadBytesUtils.readNBytesAsInt(packetLength, 0, 3);
            //logger.info("Version {} length {} remaining {}", HexFormat.of().toHexDigits(version),len,buffer.remaining());
            // do we have all bytes in the buffer?, we already read 4 octets !
            buffer.position(originalPos);

            if(buffer.remaining()<(len)) {
                logger.info("partial packet detected");
                break;
            }

            //rewind to original position to capture the beginning of packet

            byte[] packet = new byte[len];
            buffer.get(packet, 0, len);
            DiameterPacketHeader header = ReadDiameterHeader.readDiameterHeaderFromBytes(packet);
            DiameterPacket diameterPacket = diameterPacketFactory.of(header,packet);
            //logger.info("full packet added to list");
            if(diameterPacket!=null) {
                packetCounter.incrementAndGet();
                diameterPacketList.add(diameterPacket);
            }
        }

        return diameterPacketList;
    }

    private void register(Selector selector, ServerSocketChannel serverSocket)
            throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        logger.info("register client {}",client.getRemoteAddress().toString());
    }



}

