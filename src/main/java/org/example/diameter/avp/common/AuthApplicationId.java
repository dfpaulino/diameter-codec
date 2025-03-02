package org.example.diameter.avp.common;


import org.example.diameter.Utils.ReadBytesUtils;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

public class AuthApplicationId extends Avp<Integer> {
    public static int avpCode = 258;
    public static byte flags = 0x40;

    public AuthApplicationId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific() ? 12 : 8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        return ReadBytesUtils.readNBytesAsInt(buffer, offset, 4);
    }
}
