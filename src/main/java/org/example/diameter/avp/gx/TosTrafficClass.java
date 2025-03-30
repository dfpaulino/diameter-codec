package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

@AvpRegister(avpCode =1014,avpBuilderMethod = "byteToAvp")
public class TosTrafficClass extends Avp<byte[]> {
    public static int avpCode = 1014;
    public static byte flags = (byte) 0xc0;

    public TosTrafficClass(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public TosTrafficClass(byte[] data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(TosTrafficClass::new);
    }
    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctetStringDecoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,this.getData().length, VendorId.GPP.getValue(),
                EncodeUtils.OctectStringToBytes(this.getData()));
    }
}
