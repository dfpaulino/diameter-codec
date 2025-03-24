package org.example.diameter.avp.common;

import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.avp.types.Address;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

@AvpRegister(avpCode =257,avpBuilderMethod = "byteToAvp")
public class HostIpAddress extends Avp<Address> {
    public static int avpCode = 257;
    public static byte flags = (byte) 0x40;

    public HostIpAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public HostIpAddress(Address data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((HostIpAddress::new));
    }
    @Override
    public Address decode(byte[] buffer, int position, AvpHeader header) {
        return AvpTypeDecoders.AddressDecoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        int len = this.getData().getFamily()==1?(2+4):(2+16);
        return EncodeAvp.encode(avpCode,flags,len, 0,
                EncodeUtils.AddressToBytes(this.getData().getIp(),this.getData().getFamily()));
    }
}
