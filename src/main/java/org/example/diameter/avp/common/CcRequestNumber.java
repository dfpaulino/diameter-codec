package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

import static org.example.diameter.avp.AvpDecoders.Integer32Decoder;

public class CcRequestNumber extends Avp<Integer> {
    public static int avpCode = 415;
    public static byte flags = 0x40;

    public CcRequestNumber(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return Integer32Decoder.decode(buffer, position, header);
    }
}
