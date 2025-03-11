package org.example.diameter.avp.common;

import org.example.diameter.avp.*;

@AvpRegister(avpCode =6,avpBuilderMethod = "byteToAvp")
public class GppSgsnAddress extends Avp<byte[]> {
    public static int avpCode = 6;
    public static byte flags = (byte) 0x40;

    public GppSgsnAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((GppSgsnAddress::new));
    }

    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctetStringDecoder.decode(buffer, position, header);
    }
}
