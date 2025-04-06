package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;


@AvpRegister(avpCode =507,avpBuilderMethod = "byteToAvp")
public class FlowDescription extends Avp<String> {
    public static int avpCode = 507;
    public static byte flags = (byte)0xc0;

    public FlowDescription(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public FlowDescription(String data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((FlowDescription::new));
    }

    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctectStringUTF8Decoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,this.getData().length(), VendorId.GPP.getValue(),
                EncodeUtils.OctectStringUTF8ToBytes(this.getData()));
    }
}
