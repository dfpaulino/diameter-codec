package org.example.diameter.avp.common;

import org.example.diameter.avp.*;

@AvpRegister(avpCode =450,avpBuilderMethod = "byteToAvp")
public class SubscriptionIdType extends Avp<Integer> {
    public static int avpCode = 450;
    public static byte flags = 0x40;

    public SubscriptionIdType(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((SubscriptionIdType::new));
    }
    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.Integer32Decoder.decode(buffer, position, header);
    }
}
