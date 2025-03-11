package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;

@AvpRegister(avpCode =503,avpBuilderMethod = "byteToAvp")
public class AccessNetworkChargingIdentifierValue extends Avp<byte[]> {
    public static int avpCode = 503;
    public static byte flags = (byte) 0xc0;

    public AccessNetworkChargingIdentifierValue(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }
    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(AccessNetworkChargingIdentifierValue::new);
    }
    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctetStringDecoder.decode(buffer, position, header);
    }
}
