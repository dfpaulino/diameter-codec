package org.example.diameter.avp.rx;

import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.avp.types.Address;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

@AvpRegister(avpCode = 501,avpBuilderMethod = "byteToAvp")
public class AccessNetworkChargingAddress extends Avp<Address> {
    public static int avpCode = 501;
    public static byte flags = (byte) 0xc0;

    public AccessNetworkChargingAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public AccessNetworkChargingAddress(Address data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(AccessNetworkChargingAddress::new);
    }
    @Override
    public Address decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.AddressDecoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        int len = this.getData().getFamily()==1?(2+4):(2+16);
        return EncodeAvp.encode(avpCode,flags,len, VendorId.GPP.getValue(),
                EncodeUtils.AddressToBytes(this.getData().getIp(),this.getData().getFamily()));
    }
}
