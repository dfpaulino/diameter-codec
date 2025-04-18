package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.OctectStringUTF8Decoder;
@AvpRegister(avpCode =460,avpBuilderMethod = "byteToAvp")
public class UserEquipmentInfoValue extends Avp<String> {
    public static int avpCode = 460;
    public static byte flags = (byte) 0x40;

    public UserEquipmentInfoValue(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public UserEquipmentInfoValue(String data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((UserEquipmentInfoValue::new));
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
