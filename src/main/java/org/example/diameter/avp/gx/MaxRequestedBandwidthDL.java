package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;

@AvpRegister(avpCode = 515,avpBuilderMethod = "byteToAvp")
public class MaxRequestedBandwidthDL extends Avp<Integer> {
    public static int avpCode = 515;
    public static byte flags = (byte) 0xc0;

    public MaxRequestedBandwidthDL(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(MaxRequestedBandwidthDL::new);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.Integer32Decoder.decode(buffer, position, header);
    }
}
