package org.example.diameter.avp.common;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.utils.EncodeAvp;

/*
Supported-Features ::= < AVP header: 628 10415 >
{ Vendor-Id }
{ Feature-List-ID }
{ Feature-List }
 */
@AvpRegister(avpCode =628,avpBuilderMethod = "byteToAvp")
public class SupportedFeatures extends Avp<SupportedFeatures> {
    public static int avpCode = 628;
    public static byte flags = (byte) 0xc0;

    @InnerAvp@Getter
    @Setter
    private VendorId vendorId;
    @InnerAvp@Getter
    @Setter
    private FeatureListId featureListId;
    @InnerAvp@Getter
    @Setter
    private FeatureList featureList;

    public SupportedFeatures(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public SupportedFeatures() {
        super(null);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((SupportedFeatures::new));
    }

    @Override
    public SupportedFeatures returnContent() {
        return this;
    }

    @Override
    public SupportedFeatures decode(byte[] buffer, int position, AvpHeader header) {
        return (SupportedFeatures) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags, org.example.diameter.avp.enums.VendorId.GPP.getValue());
    }
}
