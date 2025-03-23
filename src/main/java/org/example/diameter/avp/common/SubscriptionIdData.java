package org.example.diameter.avp.common;

import org.example.diameter.avp.*;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

@AvpRegister(avpCode =444,avpBuilderMethod = "byteToAvp")
public class SubscriptionIdData extends Avp<String> {
    public static int avpCode = 444;
    public static byte flags = 0x40;

    public SubscriptionIdData(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public SubscriptionIdData(String data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((SubscriptionIdData::new));
    }
    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctectStringUTF8Decoder.decode(buffer, position, header);
    }
    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,this.getData().length(),0,
                EncodeUtils.OctectStringUTF8ToBytes(this.getData()));
    }
}
