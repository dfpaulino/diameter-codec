package org.example.diameter.avp.common;


import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;

import static org.example.diameter.avp.AvpTypeDecoders.Integer32Decoder;

@AvpRegister(avpCode =439,avpBuilderMethod = "byteToAvp")
public class ServiceIdentifier extends Avp<Integer> {
    public static int avpCode = 439;
    public static byte flags = (byte) 0x40;

    public ServiceIdentifier(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((ServiceIdentifier::new));
    }
    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return Integer32Decoder.decode(buffer, position, header);
    }
}
