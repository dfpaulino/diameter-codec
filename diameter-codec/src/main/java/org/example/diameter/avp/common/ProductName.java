package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.OctectStringUTF8Decoder;

@AvpRegister(avpCode =269,avpBuilderMethod = "byteToAvp")
public class ProductName extends Avp<String> {
    public static int avpCode = 269;
    public static byte flags = (byte) 0x00;

    public ProductName(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public ProductName(String data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((ProductName::new));
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
