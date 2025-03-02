package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class SubscriptionIdType extends Avp<Integer> {
    public static int avpCode = 450;
    public static byte flags = 0x40;

    public SubscriptionIdType(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpDecoders.Integer32Decoder.decode(buffer, position, header);
    }
}
