package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

import java.nio.charset.StandardCharsets;

public class ProductName extends Avp<String> {
    public static int avpCode = 269;
    public static byte flags = (byte) 0xc0;

    public ProductName(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int headerSize = (header.isVendorSpecific() ? 12 : 8);
        int offset = headerSize + position;
        int dataLen = (this.getHeader().getAvpLength() - headerSize);
        // AVP of type Octet os padded to be multiple of 4 octets
        return new String(buffer, offset, dataLen, StandardCharsets.UTF_8);
    }
}
