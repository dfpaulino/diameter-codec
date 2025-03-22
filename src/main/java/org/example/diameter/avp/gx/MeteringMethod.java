package org.example.diameter.avp.gx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.avp.enums.MeteringMethodType;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.Integer32Decoder;

@AvpRegister(avpCode = 1007,avpBuilderMethod = "byteToAvp")
public class MeteringMethod extends Avp<MeteringMethodType> {
    public static int avpCode = 1007;
    public static byte flags = (byte) 0xc0;

    public MeteringMethod(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public MeteringMethod(MeteringMethodType data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(MeteringMethod::new);
    }

    @Override
    public MeteringMethodType decode(byte[] buffer, int position, AvpHeader header) {
        return MeteringMethodType.of(Integer32Decoder.decode(buffer, position, header));
    }
    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,4, VendorId.GPP.getValue(),
                EncodeUtils.encodeIntTo4Bytes(this.getData().getType()));
    }
}
