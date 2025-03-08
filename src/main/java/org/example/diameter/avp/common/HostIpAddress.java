package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.types.Address;
import org.javatuples.Pair;

public class HostIpAddress extends Avp<Address> {
    public static int avpCode = 257;
    public static byte flags = (byte) 0x40;

    public HostIpAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Address decode(byte[] buffer, int position, AvpHeader header) {
        return AvpDecoders.AddressDecoder.decode(buffer,position,header);
    }

    int getAvpCode(){return avpCode;}
}
