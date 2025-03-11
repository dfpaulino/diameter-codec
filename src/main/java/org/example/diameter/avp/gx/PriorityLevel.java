package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;

@AvpRegister(avpCode = 1046,avpBuilderMethod = "byteToAvp")
public class PriorityLevel extends Avp<Integer> {
    public static int avpCode = 1046;
    public static byte flags = (byte) 0x80;

    public PriorityLevel(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(PriorityLevel::new);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.Integer32Decoder.decode(buffer, position, header);
    }
}
