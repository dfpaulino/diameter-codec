package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;

@AvpRegister(avpCode = 1025,avpBuilderMethod = "byteToAvp")
public class GuaranteedBitrateDL extends Avp<Integer> {
    public static int avpCode = 1025;
    public static byte flags = (byte) 0xc0;

    public GuaranteedBitrateDL(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(GuaranteedBitrateDL::new);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.Integer32Decoder.decode(buffer, position, header);
    }
}
