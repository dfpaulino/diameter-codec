package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

import static org.example.diameter.avp.AvpDecoders.OctectStringUTF8Decoder;

/*
 Each AVP of type OctetString MUST be padded to align on a 32-bit
   boundary, while other AVP types align naturally.  A number of zero-
   valued bytes are added to the end of the AVP Data field till a word
   boundary is reached.  The length of the padding is not reflected in
   the AVP Length field.
 */
public class SessionId extends Avp<String> {
    public static int avpCode = 263;
    public static byte flags = 0x40;

    public SessionId(AvpHeader header, byte[] rawData, int position) {
        super(header, rawData, position);
    }


    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return OctectStringUTF8Decoder.decode(buffer, position, header);
    }
}
