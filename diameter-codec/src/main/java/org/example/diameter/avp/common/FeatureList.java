package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.Integer32Decoder;
import static org.example.diameter.avp.AvpTypeDecoders.OctetStringDecoder;

@AvpRegister(avpCode =630,avpBuilderMethod = "byteToAvp")
public class FeatureList extends Avp<byte[]> {
    public static int avpCode = 630;
    public static byte flags = (byte) 0x80;

    public FeatureList(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public FeatureList(byte[] data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((FeatureList::new));
    }
    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return OctetStringDecoder.decode(buffer, position, header);
    }
    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,4, VendorId.GPP.getValue(),
                this.getData());
    }
}
