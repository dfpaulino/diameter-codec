package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;

@AvpRegister(avpCode =1066,avpBuilderMethod = "byteToAvp")
public class MonitoringKey extends Avp<byte[]> {
    public static int avpCode = 1066;
    public static byte flags = (byte) 0xc0;

    public MonitoringKey(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }
    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(MonitoringKey::new);
    }
    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.OctetStringDecoder.decode(buffer, position, header);
    }
}
