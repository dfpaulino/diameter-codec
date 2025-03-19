package org.example.diameter.avp.common;

import org.example.diameter.avp.*;

@AvpRegister(avpCode =7,avpBuilderMethod = "byteToAvp")
public class GppGgsnAddress extends Avp<byte[]> {
    public static int avpCode = 7;
    public static byte flags = (byte) 0x80;

    public GppGgsnAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((GppGgsnAddress::new));
    }

    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctetStringDecoder.decode(buffer, position, header);
    }
}
