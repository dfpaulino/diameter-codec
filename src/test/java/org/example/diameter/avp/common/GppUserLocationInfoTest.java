package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GppUserLocationInfoTest {
    byte[] buffer = HexFormat.of().parseHex("00000016c0000014000028af0064f6790001ea6c");
    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);
        Avp<byte[]> userLocationInfoAvp = new GppUserLocationInfo(header,buffer,0);
        byte[] uELoc = userLocationInfoAvp.getData();

        assertThat(header.getAvpCode()).isEqualTo(GppUserLocationInfo.avpCode);
        assertThat(header.getAvpFlags()).isEqualTo(GppUserLocationInfo.flags);
        assertThat(header.getAvpLength()).isEqualTo(20);
        assertThat(header.getPaddingSize()).isEqualTo(0);

        assertThat(uELoc).isEqualTo(HexFormat.of().parseHex("0064f6790001ea6c"));
    }
}