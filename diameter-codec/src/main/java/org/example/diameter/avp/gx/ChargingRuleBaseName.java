package org.example.diameter.avp.gx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.OctectStringUTF8Decoder;

@AvpRegister(avpCode =1004,avpBuilderMethod = "byteToAvp")
public class ChargingRuleBaseName extends Avp<String> {
    public static int avpCode = 1004;
    public static byte flags = (byte)0xc0;

    public ChargingRuleBaseName(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public ChargingRuleBaseName(String data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((ChargingRuleBaseName::new));
    }

    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return OctectStringUTF8Decoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,this.getData().length(), VendorId.GPP.getValue(),
                EncodeUtils.OctectStringUTF8ToBytes(this.getData()));
    }

}
