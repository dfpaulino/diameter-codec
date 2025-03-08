package org.example.diameter.avp.common;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;
/*
Supported-Features ::= < AVP header: 628 10415 >
{ Vendor-Id }
{ Feature-List-ID }
{ Feature-List }
 */
public class SupportedFeatures extends Avp<SupportedFeatures> {
    public static int avpCode = 628;
    public static byte flags = (byte) 0xc0;

    @Getter @Setter
    private VendorId vendorId;
    @Getter @Setter
    private FeatureListId featureListId;
    @Getter @Setter
    private FeatureList featureList;

    public SupportedFeatures(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public SupportedFeatures decode(byte[] buffer, int position, AvpHeader header) {
        return (SupportedFeatures) AvpDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }
}
