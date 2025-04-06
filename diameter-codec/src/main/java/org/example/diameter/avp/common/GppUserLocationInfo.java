package org.example.diameter.avp.common;

import org.example.diameter.avp.*;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

@AvpRegister(avpCode =22,avpBuilderMethod = "byteToAvp")
public class GppUserLocationInfo extends Avp<byte[]> {
    public static int avpCode = 22;
    public static byte flags = (byte) 0xc0;

    public GppUserLocationInfo(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }
    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((GppUserLocationInfo::new));
    }

    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctetStringDecoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,this.getData().length, 0,
                EncodeUtils.OctectStringToBytes(this.getData()));
    }
}
