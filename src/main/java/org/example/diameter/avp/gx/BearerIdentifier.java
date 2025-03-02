package org.example.diameter.avp.gx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class BearerIdentifier extends Avp<String> {
    public static int avpCode = 1020;
    public static byte flags = (byte) 0xc0;

    public BearerIdentifier(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return AvpDecoders.OctectStringUTF8Decoder.decode(buffer, position, header);
    }
}
