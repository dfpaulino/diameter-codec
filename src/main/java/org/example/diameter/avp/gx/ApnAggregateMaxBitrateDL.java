package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;

@AvpRegister(avpCode =1040,avpBuilderMethod = "byteToAvp")
public class ApnAggregateMaxBitrateDL extends Avp<Integer> {
    public static int avpCode = 1040;
    public static byte flags = (byte) 0x80;

    public ApnAggregateMaxBitrateDL(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(ApnAggregateMaxBitrateDL::new);
    }
    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.Integer32Decoder.decode(buffer, position, header);
    }
}
