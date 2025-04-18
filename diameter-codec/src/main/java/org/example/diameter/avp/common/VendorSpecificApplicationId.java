package org.example.diameter.avp.common;


import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.utils.EncodeAvp;

@AvpRegister(avpCode =260,avpBuilderMethod = "byteToAvp")
public class VendorSpecificApplicationId extends Avp<VendorSpecificApplicationId> {
    public static int avpCode = 260;
    public static byte flags = (byte) 0x40;
    @InnerAvp@Getter@Setter
    private VendorId vendorId;
    @InnerAvp@Getter@Setter
    private AuthApplicationId authApplicationId;

    public VendorSpecificApplicationId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public VendorSpecificApplicationId() {
        super(null);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((VendorSpecificApplicationId::new));
    }

    @Override
    public VendorSpecificApplicationId returnContent() {
        return this;
    }

    @Override
    public VendorSpecificApplicationId decode(byte[] buffer, int position, AvpHeader header) {
        return (VendorSpecificApplicationId) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }
    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags,0);
    }
}
