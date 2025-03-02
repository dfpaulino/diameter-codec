package org.example.diameter.avp.gx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class AccessNetworkChargingIdentifierValue extends Avp<byte[]> {
    public static int avpCode = 503;
    public static byte flags = (byte) 0xc0;

    public AccessNetworkChargingIdentifierValue(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public byte[] decode(byte[] buffer, int position, AvpHeader header) {
        return AvpDecoders.OctetStringDecoder.decode(buffer, position, header);
    }
}
