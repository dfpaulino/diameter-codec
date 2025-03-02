package org.example.diameter.avp.gx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class ApnAggregateMaxBitrateDL extends Avp<Integer> {
    public static int avpCode = 1040;
    public static byte flags = (byte) 0x80;

    public ApnAggregateMaxBitrateDL(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpDecoders.Integer32Decoder.decode(buffer, position, header);
    }
}
