package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

import static org.example.diameter.avp.AvpDecoders.OctectStringUTF8Decoder;

public class UserEquipmentInfoValue extends Avp<String> {
    public static int avpCode = 460;
    public static byte flags = (byte) 0x40;

    public UserEquipmentInfoValue(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return OctectStringUTF8Decoder.decode(buffer, position, header);
    }
}
