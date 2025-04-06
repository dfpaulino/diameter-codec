package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.OctectStringUTF8Decoder;
@AvpRegister(avpCode =296,avpBuilderMethod = "byteToAvp")
public class OriginRealm extends Avp<String> {
    public static int avpCode = 296;
    public static byte flags = 0x40;

    public OriginRealm(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public OriginRealm(String data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((OriginRealm::new));
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
