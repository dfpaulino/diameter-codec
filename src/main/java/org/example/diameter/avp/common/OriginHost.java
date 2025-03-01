package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

import java.nio.charset.StandardCharsets;

import static org.example.diameter.avp.AvpDecoders.OctectStringDecoder;

/*
 Each AVP of type OctetString MUST be padded to align on a 32-bit
   boundary, while other AVP types align naturally.  A number of zero-
   valued bytes are added to the end of the AVP Data field till a word
   boundary is reached.  The length of the padding is not reflected in
   the AVP Length field.
 */
public class OriginHost extends Avp<String> {

    public OriginHost(AvpHeader header, byte[] rawData,int position) {
        super(header,rawData,position);
    }


    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return OctectStringDecoder.decode(buffer,position,header);
    }
}
