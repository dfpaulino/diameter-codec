package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

@AvpRegister(avpCode = 1028,avpBuilderMethod = "byteToAvp")
public class QoSClassIdentifier extends Avp<Integer> {
    public static int avpCode = 1028;
    public static byte flags = (byte) 0xc0;

    public QoSClassIdentifier(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public QoSClassIdentifier(Integer data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(QoSClassIdentifier::new);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.Integer32Decoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,4, VendorId.GPP.getValue(),
                EncodeUtils.encodeIntTo4Bytes(this.getData()));
    }
}
