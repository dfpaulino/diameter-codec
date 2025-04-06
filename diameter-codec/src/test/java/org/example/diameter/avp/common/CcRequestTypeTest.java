package org.example.diameter.avp.common;


import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CcRequestTypeTest {
    byte[] buffer = HexFormat.of().parseHex("000001a04000000c00000001");

    @Test
    public void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer, 0);
        CcRequestType ccRequestType = new CcRequestType(header, buffer, 0);
        assertThat(header.getAvpLength()).isEqualTo(12);
        assertThat(header.getAvpFlags()).isEqualTo((byte) 0x40);
        assertThat(header.getAvpCode()).isEqualTo(416);
        assertThat(header.getPaddingSize()).isEqualTo(0);
        assertThat(ccRequestType.getData()).isEqualTo(1);
    }

    @Test
    public void byteToAvp() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer, 0);
        AvpBuilder builder =CcRequestType.byteToAvp();
        Avp obj = builder.builder().createAvp(header,buffer,0);
        assertThat(obj).isInstanceOf(CcRequestType.class);
    }

    @Test
    public void encode() {
        CcRequestType ccRequestType = new CcRequestType(1);
        byte[] bytes = ccRequestType.encode();
        assertThat(bytes).isEqualTo(buffer);
    }
}