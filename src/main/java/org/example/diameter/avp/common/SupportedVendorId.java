package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadBytesUtils;

public class SupportedVendorId extends Avp<Integer> {
    public static int avpCode = 265;
    public static byte flags = (byte) 0xc0;

    public SupportedVendorId(AvpHeader header, byte[] buffer, int position) {
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
