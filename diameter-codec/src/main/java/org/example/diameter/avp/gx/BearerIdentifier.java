package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

@AvpRegister(avpCode =1020,avpBuilderMethod = "byteToAvp")
public class BearerIdentifier extends Avp<byte[]> {
    public static int avpCode = 1020;
    public static byte flags = (byte) 0xc0;

    public BearerIdentifier(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public BearerIdentifier(byte[] data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(BearerIdentifier::new);
    }
    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctetStringDecoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,4, VendorId.GPP.getValue(),
                EncodeUtils.OctectStringToBytes(this.getData()));
    }
}
