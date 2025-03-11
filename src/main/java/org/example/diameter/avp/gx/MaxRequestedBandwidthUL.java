package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;

@AvpRegister(avpCode = 516,avpBuilderMethod = "byteToAvp")
public class MaxRequestedBandwidthUL extends Avp<Integer> {
    public static int avpCode = 516;
    public static byte flags = (byte) 0xc0;

    public MaxRequestedBandwidthUL(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(MaxRequestedBandwidthUL::new);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.Integer32Decoder.decode(buffer, position, header);
    }
}
