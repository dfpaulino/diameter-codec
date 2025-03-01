package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class SubscriptionIdData extends Avp<String> {
    public SubscriptionIdData(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return AvpDecoders.OctectStringDecoder.decode(buffer, position, header);
    }
}
