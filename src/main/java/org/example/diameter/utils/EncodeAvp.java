package org.example.diameter.utils;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.InnerAvp;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
4.1.  AVP Header
Each AVP of type OctetString MUST be padded to align on a 32-bit
   boundary, while other AVP types align naturally.  A number of zero-
   valued bytes are added to the end of the AVP Data field till a word
   boundary is reached.  The length of the padding is not reflected in
   the AVP Length field.


   The fields in the AVP header MUST be sent in network byte order.  The
   format of the header is:

    0                   1                   2                   3
    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                           AVP Code                            |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |V M P r r r r r|                  AVP Length                   |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                        Vendor-ID (opt)                        |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |    Data ...
   +-+-+-+-+-+-+-+-+
 */
public class EncodeAvp {

    public static byte[] encode(int avpCode, byte flags, int length, int vendorId, byte[] payload) {
        int headerSize = (isVendorSpecific(flags, vendorId) ? 12 : 8);
        int bufferEstimatedSize = headerSize + payload.length;
        ByteBuffer buffer = ByteBuffer.allocate(bufferEstimatedSize);

        buffer.put(EncodeUtils.encodeIntTo4Bytes(avpCode));
        buffer.put(flags);
        //note that length can be < payload.length due to padding
        buffer.put(EncodeUtils.encodeIntToNBytes((headerSize + length), 3));
        if (isVendorSpecific(flags, vendorId)) {
            buffer.put(EncodeUtils.encodeIntToNBytes(vendorId, 4));
        }
        buffer.put(payload);
        buffer.flip();
        byte[] bytes = new byte[bufferEstimatedSize];
        buffer.get(bytes);
        return bytes;
    }

    public static byte[] encodeGroup(Avp<?> self, int avpCode, byte flags, int vendorId) {
        List<byte[]> innerAvpsBytes = new ArrayList<>();
        // get all fields with annotation InnerAvp
        List<Field> fields = Arrays.stream(self.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(InnerAvp.class)).toList();
        //TODO order fields by InnerAvp order()

        for (Field field : fields) {
            if (field.getAnnotation(InnerAvp.class).isCollection()) {
                try {
                    field.setAccessible(true);
                    Collection<Avp<?>> avps = (Collection<Avp<?>>) field.get(self);
                    field.setAccessible(false);
                    avps.forEach(avp -> innerAvpsBytes.add(avp.encode()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    field.setAccessible(true);
                    Avp<?> avp = (Avp<?>) (field.get(self));
                    field.setAccessible(false);
                    innerAvpsBytes.add(avp.encode());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        int innerPayloadSize=0;
        for (byte[] b:innerAvpsBytes) {
            innerPayloadSize+=b.length;
        }
        byte[] innerPayload = new byte[innerPayloadSize];
        //ByteBuffer byteBuffer = ByteBuffer.allocate(innerPayloadSize);
        int destPos=0;
        for (byte[] b:innerAvpsBytes) {
            // equiv of memcopy in the best lang C!!!
            System.arraycopy(b,0,innerPayload,destPos,b.length);
            destPos+=b.length;
        }

        return encode(avpCode,flags,innerPayload.length,vendorId,innerPayload);
    }

    private static boolean isVendorSpecific(byte flags, int vendor) {
        return (flags & 0x80) == 0x80 && vendor > 0;
    }
}
