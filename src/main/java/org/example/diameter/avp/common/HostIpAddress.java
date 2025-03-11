package org.example.diameter.avp.common;

import org.example.diameter.avp.*;
import org.example.diameter.avp.types.Address;
@AvpRegister(avpCode =257,avpBuilderMethod = "byteToAvp")
public class HostIpAddress extends Avp<Address> {
    public static int avpCode = 257;
    public static byte flags = (byte) 0x40;

    public HostIpAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((HostIpAddress::new));
    }
    @Override
    public Address decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.AddressDecoder.decode(buffer, position, header);
    }

    int getAvpCode() {
        return avpCode;
    }
}
