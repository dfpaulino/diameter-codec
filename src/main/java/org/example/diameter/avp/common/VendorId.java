package org.example.diameter.avp.common;


import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.Integer32Decoder;

@AvpRegister(avpCode =266,avpBuilderMethod = "byteToAvp")
public class VendorId extends Avp<Integer> {
    public static int avpCode = 266;
    public static byte flags = (byte) 0x40;

    public VendorId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public VendorId(Integer data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((VendorId::new));
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return Integer32Decoder.decode(buffer, position, header);
    }
    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,4,0,
                EncodeUtils.encodeIntTo4Bytes(this.getData()));
    }
}
