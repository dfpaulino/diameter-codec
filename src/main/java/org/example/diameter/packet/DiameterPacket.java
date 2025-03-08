package org.example.diameter.packet;

import lombok.Getter;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpIdToAvpMapper;
import org.example.diameter.utils.ReadAvpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
.  Diameter Header

   A summary of the Diameter header format is shown below.  The fields
   are transmitted in network byte order.

    0                   1                   2                   3
    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |    Version    |                 Message Length                |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   | command flags |                  Command-Code                 |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                         Application-ID                        |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                      Hop-by-Hop Identifier                    |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                      End-to-End Identifier                    |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |  AVPs ...
   +-+-+-+-+-+-+-+-+-+-+-+-+
 */
// Global Diamter Packet

public abstract class DiameterPacket<T> {
    private static final Logger logger = LoggerFactory.getLogger(DiameterPacket.class);
    @Getter
    private final DiameterPacketHeader header;
    // The whole data as byte[]
    private byte[] buffer;
    // object data (decoded format)
    @Getter
    private T data;

    public DiameterPacket(DiameterPacketHeader header, byte[] buffer) {
        this.header = header;
        this.buffer = buffer;
        this.data = this.decode(header, buffer);
    }

    public DiameterPacket(DiameterPacketHeader header, T data) {
        this.header = header;
        this.data = data;
    }

    public T getData() {
        if (this.data == null) {
            this.data = this.decode(this.header, this.buffer);
        }
        return this.data;
    }

    public byte[] getBuffer() {
        if (this.buffer.length == 0) {
            this.buffer = this.encode();
        }
        return buffer;
    }

    public abstract T decode(DiameterPacketHeader header, byte[] buffer);

    public void decode() {
        byte[] bytes = this.getBuffer();
        // skip header
        int position = 20;
        while (position < this.getHeader().getMessageLength()) {
            //decode AVP Header
            AvpHeader avpHeaderheader = ReadAvpHeader.readAvpHeaderFromBytes(buffer, position);
            // what AVP is this?
            // if supported....else skip and push position for next avp based on avpHeader lengh
            //read Data from AVP
            //create instance of avp eg new OriginHost(headers,buffer,position,)
            AvpIdToAvpMapper.AvpDefinition avpDefinition = AvpIdToAvpMapper.getAvpMapper().get(avpHeaderheader
                    .getAvpCode());
            if (avpDefinition != null) {
                Avp<?> avp = avpDefinition.avpCreator.createInstance(avpHeaderheader, buffer, position);
                String methodName = "set" + avp.getClass().getSimpleName();
                try {
                    //Method method = this.getClass().getDeclaredMethod(methodName, avp.getClass());
                    Method method = this.getClass().getDeclaredMethod(methodName, avp.getClass());
                    method.invoke(this, avp);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    logger.warn("AVP [{}] seems does not belong to this group... ignoring", avp.getClass().getSimpleName());
                    logger.warn("Exception {}", e);
                    //throw new RuntimeException(e);
                }
            } else {
                logger.warn("Unknown AVP ID [{}]", avpHeaderheader.getAvpCode());
            }

            position += avpHeaderheader.getAvpLength() + avpHeaderheader.getPaddingSize();
        }
    }

    private byte[] encode() {
        return new byte[1024];
    }
}