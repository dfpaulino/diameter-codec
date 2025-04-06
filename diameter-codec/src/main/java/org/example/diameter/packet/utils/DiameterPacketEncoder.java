package org.example.diameter.packet.utils;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.InnerAvp;
import org.example.diameter.packet.DiameterPacket;
import org.example.diameter.packet.DiameterPacketHeader;
import org.example.diameter.utils.EncodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
public class DiameterPacketEncoder {

    private static final Logger logger = LoggerFactory.getLogger(DiameterPacketEncoder.class);

    public static <T extends DiameterPacket> byte[] encode(T self) {

        List<byte[]> innerAvpsBytes = new ArrayList<>();
        // get all fields with annotation InnerAvp
        List<Field> fields = Arrays.stream(self.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(InnerAvp.class)).toList();
        //TODO order fields by InnerAvp order()

        for (Field field : fields) {
            try {
                if (field.getAnnotation(InnerAvp.class).isCollection()) {
                    field.setAccessible(true);
                    Collection<Avp<?>> avps = (Collection<Avp<?>>) field.get(self);
                    if (null != avps) {
                        avps.forEach(avp -> innerAvpsBytes.add(avp.encode()));
                    }
                    field.setAccessible(false);
                } else {
                    field.setAccessible(true);
                    Avp<?> avp = (Avp<?>) (field.get(self));
                    if (null != avp) {
                        innerAvpsBytes.add(avp.encode());
                    }
                    field.setAccessible(false);
                }
            } catch (IllegalAccessException e) {
                logger.error("cannot encode AVP {}", field.getName());
            }
        }
        // calc avp size
        int innerPayloadSize = 0;
        for (byte[] b : innerAvpsBytes) {
            innerPayloadSize += b.length;
        }

        // 20 bytes for header
        ByteBuffer buffer = ByteBuffer.allocate(20+innerPayloadSize );
        // append header
        buffer.put(self.getHeader().getVersion());
        buffer.put(EncodeUtils.encodeIntToNBytes(innerPayloadSize+20,3));
        buffer.put(self.getHeader().getCommandFlags());
        buffer.put(EncodeUtils.encodeIntToNBytes(self.getHeader().getCommandCode(),3));
        buffer.put(EncodeUtils.encodeLongTo4Bytes(self.getHeader().getApplicationId()));
        buffer.put(EncodeUtils.encodeLongTo4Bytes(self.getHeader().getHopByHop()));
        buffer.put(EncodeUtils.encodeLongTo4Bytes(self.getHeader().getEnd2End()));

        // append avp data
        for (byte[] b : innerAvpsBytes) {
            buffer.put(b);
        }
        buffer.flip();
        return buffer.array();
    }

}
