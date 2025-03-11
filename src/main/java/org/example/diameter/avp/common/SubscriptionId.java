package org.example.diameter.avp.common;

import lombok.Getter;
import org.example.diameter.avp.*;

@AvpRegister(avpCode =443,avpBuilderMethod = "byteToAvp")
public class SubscriptionId extends Avp<SubscriptionId> {
    public static int avpCode = 443;
    public static byte flags = 0x40;
    @Getter
    private SubscriptionIdType subscriptionIdType;
    @Getter
    private SubscriptionIdData subscriptionIdData;

    public SubscriptionId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }


    private void setSubscriptionIdType(SubscriptionIdType subscriptionIdType) {
        this.subscriptionIdType = subscriptionIdType;
    }

    private void setSubscriptionIdData(SubscriptionIdData subscriptionIdData) {
        this.subscriptionIdData = subscriptionIdData;
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((SubscriptionId::new));
    }

    @Override
    public SubscriptionId decode(byte[] buffer, int position, AvpHeader header) {
        return (SubscriptionId) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

}
