package org.example.diameter.avp.gx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.Integer32Decoder;
@AvpRegister(avpCode = 1030,avpBuilderMethod = "byteToAvp")
public class QoSUpgrade extends Avp<Integer> {
    public static int avpCode = 1030;
    public static byte flags = (byte) 0xc0;

    public QoSUpgrade(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public QoSUpgrade(Integer data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(QoSUpgrade::new);
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
