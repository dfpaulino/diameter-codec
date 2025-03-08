package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class GppSgsnAddress extends Avp<byte[]> {
    public static int avpCode = 6;
    public static byte flags = (byte) 0x40;

    public GppSgsnAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return AvpDecoders.OctetStringDecoder.decode(buffer, position, header);
    }
}
