package org.example.diameter;


import org.example.diameter.packet.DiameterPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HexFormat;

@Service
public class DiameterSocketWriter {
    private static final Logger logger = LoggerFactory.getLogger(DiameterSocketWriter.class);

    public void write(DiameterReqContext context) {
        try {

            byte[] encoded = context.getResponse().encode();
            SocketChannel channel = context.getSocketChannel();
            if(logger.isDebugEnabled()) {
                logger.debug("packet {} write to socket {}", HexFormat.of().formatHex(encoded),
                        context.getSocketChannel().getRemoteAddress().toString());
            }
            // concurrent write to same channel.
            synchronized (channel) {
                channel.write(ByteBuffer.wrap(encoded));
            }
        }catch (Exception e) {
            logger.error("socket write error",e);
        }



    }
}
