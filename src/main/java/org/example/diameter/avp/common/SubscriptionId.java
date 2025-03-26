package org.example.diameter.avp.common;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

@AvpRegister(avpCode =443,avpBuilderMethod = "byteToAvp")
public class SubscriptionId extends Avp<SubscriptionId> {
    public static int avpCode = 443;
    public static byte flags = 0x40;
    @InnerAvp@Getter@Setter
    private SubscriptionIdType subscriptionIdType;
    @InnerAvp@Getter@Setter
    private SubscriptionIdData subscriptionIdData;

    public SubscriptionId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public SubscriptionId(SubscriptionIdType subscriptionIdType,SubscriptionIdData subscriptionIdData) {
        super(null);
        this.subscriptionIdData = subscriptionIdData;
        this.subscriptionIdType = subscriptionIdType;
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
    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags,0);
    }
}
