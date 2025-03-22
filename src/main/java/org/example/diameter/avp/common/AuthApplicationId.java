package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;
import org.example.diameter.utils.ReadBytesUtils;

@AvpRegister(avpCode =258,avpBuilderMethod = "byteToAvp")
public class AuthApplicationId extends Avp<Integer> {
    public static int avpCode = 258;
    public static byte flags = 0x40;

    public AuthApplicationId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public AuthApplicationId(Integer data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(AuthApplicationId::new);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific() ? 12 : 8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        return ReadBytesUtils.readNBytesAsInt(buffer, offset, 4);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,4,0,
                EncodeUtils.encodeIntTo4Bytes(this.getData()));
    }
}
