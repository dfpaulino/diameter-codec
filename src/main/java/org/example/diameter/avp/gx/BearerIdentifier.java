package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;

@AvpRegister(avpCode =1020,avpBuilderMethod = "byteToAvp")
public class BearerIdentifier extends Avp<String> {
    public static int avpCode = 1020;
    public static byte flags = (byte) 0xc0;

    public BearerIdentifier(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }
    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(BearerIdentifier::new);
    }
    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctectStringUTF8Decoder.decode(buffer, position, header);
    }
}
