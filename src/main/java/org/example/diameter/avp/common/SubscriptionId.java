package org.example.diameter.avp.common;

import lombok.Getter;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class SubscriptionId extends Avp<SubscriptionId> {
    public static int avpCode = 443;
    public static byte flags = 0x40;
    @Getter
    private SubscriptionIdType subscriptionIdType;
    @Getter
    private SubscriptionIdData subscriptionIdData;

    private void setSubscriptionIdType(SubscriptionIdType subscriptionIdType) {
        this.subscriptionIdType = subscriptionIdType;
    }

    private void setSubscriptionIdData(SubscriptionIdData subscriptionIdData) {
        this.subscriptionIdData = subscriptionIdData;
    }

    public SubscriptionId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public SubscriptionId decode(byte[] buffer, int position, AvpHeader header) {
        return (SubscriptionId) AvpDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

}
