package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;

import static org.example.diameter.avp.AvpTypeDecoders.OctectStringUTF8Decoder;
@AvpRegister(avpCode =283,avpBuilderMethod = "byteToAvp")
public class DestinationRealm extends Avp<String> {
    public static int avpCode = 283;
    public static byte flags = 0x40;

    public DestinationRealm(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((DestinationRealm::new));
    }
    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return OctectStringUTF8Decoder.decode(buffer, position, header);
    }
}
