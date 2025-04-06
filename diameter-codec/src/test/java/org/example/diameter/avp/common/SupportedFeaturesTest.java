package org.example.diameter.avp.common;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SupportedFeaturesTest {
    private byte[] buffer = HexFormat.of().parseHex("00000274c0000038000028af0000010a4000000c000028af0000027580000010000028af000000010000027680000010000028af0000000b");
    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);
        SupportedFeatures supportedFeatures = new SupportedFeatures(header,buffer,0);
        assertThat(supportedFeatures.getData().getFeatureListId().getData()).isEqualTo(1);
        assertThat(supportedFeatures.getData().getFeatureList().getData()).isEqualTo(HexFormat.of().parseHex("0000000b"));
        assertThat(supportedFeatures.getData().getVendorId().getData()).isEqualTo(VendorId.GPP.getValue());
    }

    @Test
    void encode() {
        SupportedFeatures supportedFeatures = new SupportedFeatures();
        supportedFeatures.setFeatureListId(new FeatureListId(1));
        supportedFeatures.setFeatureList(new FeatureList(HexFormat.of().parseHex("0000000b")));
        supportedFeatures.setVendorId(new org.example.diameter.avp.common.VendorId(VendorId.GPP.getValue()));

        byte[] bytes = supportedFeatures.encode();

        assertThat(bytes).isEqualTo(buffer);

    }
}