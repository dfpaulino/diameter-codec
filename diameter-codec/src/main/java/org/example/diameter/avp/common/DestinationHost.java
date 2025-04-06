package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.OctectStringUTF8Decoder;
@AvpRegister(avpCode =293,avpBuilderMethod = "byteToAvp")
public class DestinationHost extends Avp<String> {
    public static int avpCode = 293;
    public static byte flags = 0x40;

    public DestinationHost(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((DestinationHost::new));
    }

    public DestinationHost(String data) {
        super(data);
    }

    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return OctectStringUTF8Decoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,this.getData().length(),0,
                EncodeUtils.OctectStringUTF8ToBytes(this.getData()));
    }
}
