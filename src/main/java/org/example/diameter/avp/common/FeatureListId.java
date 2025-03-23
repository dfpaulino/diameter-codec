package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.Integer32Decoder;
@AvpRegister(avpCode =629,avpBuilderMethod = "byteToAvp")
public class FeatureListId extends Avp<Integer> {
    public static int avpCode = 629;
    public static byte flags = (byte) 0x80;

    public FeatureListId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public FeatureListId(Integer data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((FeatureListId::new));
    }
    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return Integer32Decoder.decode(buffer, position, header);
    }
    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,4, VendorId.GPP.getValue(),
                EncodeUtils.encodeIntTo4Bytes(this.getData()));
    }
}
