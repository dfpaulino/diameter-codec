package org.example.diameter.utils;

import java.nio.ByteBuffer;

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

    public static byte[] encode(int avpCode, byte flags, int length, int vendorId,byte[] payload) {
        int headerSize = (isVendorSpecific(flags,vendorId)?12:8);
        int bufferEstimatedSize = headerSize+payload.length;
        ByteBuffer buffer = ByteBuffer.allocate(bufferEstimatedSize);

        buffer.put(EncodeUtils.encodeIntTo4Bytes(avpCode));
        buffer.put(flags);
        //note that length can be < payload.length due to padding
        buffer.put(EncodeUtils.encodeIntToNBytes((headerSize+length),3));
        if(isVendorSpecific(flags,vendorId)) {
            buffer.put(EncodeUtils.encodeIntToNBytes(vendorId,4));
        }
        buffer.put(payload);
        buffer.flip();
        byte[] bytes = new byte[bufferEstimatedSize];
        buffer.get(bytes);
        return bytes;
    }

    private static boolean isVendorSpecific(byte flags, int vendor) {
        return (flags&0x80)==0x80 && vendor>0;
    }
}
