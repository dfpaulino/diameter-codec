package org.example.diameter.avp.gx;

import org.example.diameter.avp.*;
import org.example.diameter.avp.types.Time;

@AvpRegister(avpCode = 1044,avpBuilderMethod = "byteToAvp")
public class RuleDeactivationTime extends Avp<Time> {
    public static int avpCode = 1044;
    public static byte flags = (byte) 0xc0;

    public RuleDeactivationTime(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public RuleDeactivationTime(Time data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(RuleActivationTime::new);
    }

    @Override
    public Time decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.ntpTimeDecoder.decode(buffer,position,header);
    }
}
