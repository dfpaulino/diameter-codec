package org.example.diameter.packet;

import lombok.Getter;
import org.example.diameter.Utils.ReadAvpHeader;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpIdToAvpMapper;

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

public class DiameterPacket<T> {

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
        this.decode();
    }

    public DiameterPacket(DiameterPacketHeader header, T data) {
        this.header = header;
        this.data = data;

    }

    public byte[] getBuffer() {
        if (this.buffer.length == 0) {
            this.buffer = this.encode();
        }
        return buffer;
    }

    private void decode() {
        byte[] bytes = this.getBuffer();
        // skip header
        int position = 20;
        while (position < this.getHeader().getMessageLength()) {
            //decode AVP Header
            AvpHeader avpHeaderheader = ReadAvpHeader.readAvpHeaderFromBytes(bytes, position);
            // what AVP is this?
            // if supported....else skip and push position for next avp based on avpHeader lengh
            //read Data from AVP
            //create instance of avp eg new OriginHost(headers,buffer,position,)
            Avp<?> avp = AvpIdToAvpMapper.getAvpMapper().get(avpHeaderheader
                    .getAvpCode()).avpCreator.createInstance(avpHeaderheader, bytes, position);
            String methodName = "set" + avp.getClass().getSimpleName();
            try {
                Method method = this.getClass().getDeclaredMethod(methodName, avp.getClass());
                method.invoke(data, avp);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private byte[] encode() {
        return new byte[1024];
    }
}