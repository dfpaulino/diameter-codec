package org.example.diameter.avp.rx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.types.Address;
import org.javatuples.Pair;

public class AccessNetworkChargingAddress extends Avp<Address> {
    public static int avpCode = 501;
    public static byte flags = (byte) 0xc0;

    public AccessNetworkChargingAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Address decode(byte[] buffer, int position, AvpHeader header) {
        return AvpDecoders.AddressDecoder.decode(buffer, position, header);
    }
}
