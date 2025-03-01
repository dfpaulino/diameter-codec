package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class SubscriptionIdType extends Avp<Integer> {
    public SubscriptionIdType(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpDecoders.IntegerDecoder.decode(buffer, position, header);
    }
}
