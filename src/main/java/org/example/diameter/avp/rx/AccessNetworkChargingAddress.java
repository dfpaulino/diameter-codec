package org.example.diameter.avp.rx;

import org.example.diameter.avp.*;
import org.example.diameter.avp.types.Address;
@AvpRegister(avpCode = 501,avpBuilderMethod = "byteToAvp")
public class AccessNetworkChargingAddress extends Avp<Address> {
    public static int avpCode = 501;
    public static byte flags = (byte) 0xc0;

    public AccessNetworkChargingAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }
    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(AccessNetworkChargingAddress::new);
    }
    @Override
    public Address decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.AddressDecoder.decode(buffer, position, header);
    }
}
