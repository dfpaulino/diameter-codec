package org.example.diameter.avp.common;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
@AvpRegister(avpCode =443,avpBuilderMethod = "byteToAvp")
public class SubscriptionId extends Avp<SubscriptionId> {
    public static int avpCode = 443;
    public static byte flags = 0x40;
    @Getter@Setter
    private SubscriptionIdType subscriptionIdType;
    @Getter@Setter
    private SubscriptionIdData subscriptionIdData;

    public SubscriptionId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public SubscriptionId() {
        super(null);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((SubscriptionId::new));
    }

    @Override
    public SubscriptionId decode(byte[] buffer, int position, AvpHeader header) {
        return (SubscriptionId) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

    @Override
    public SubscriptionId returnContent() {
        return this;
    }
}
