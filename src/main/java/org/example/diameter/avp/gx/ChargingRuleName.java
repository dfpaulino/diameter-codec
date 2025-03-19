package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;


@AvpRegister(avpCode =1005,avpBuilderMethod = "byteToAvp")
public class ChargingRuleName extends Avp<String> {
    public static int avpCode = 1005;
    public static byte flags = 0x40;

    public ChargingRuleName(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public ChargingRuleName(String data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((ChargingRuleName::new));
    }
    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctectStringUTF8Decoder.decode(buffer, position, header);
    }
}
