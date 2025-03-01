package org.example.diameter.avp.common;

import org.example.diameter.Utils.ReadBytesUtils;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

public class SupportedVendorId extends Avp<Integer> {
    public SupportedVendorId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific()?12:8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        return ReadBytesUtils.readNBytesAsInt(buffer,offset,4);
    }
}
