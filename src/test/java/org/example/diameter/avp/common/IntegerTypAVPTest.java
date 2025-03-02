package org.example.diameter.avp.common;

import org.example.diameter.Utils.ReadAvpHeader;
import org.example.diameter.avp.AvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IntegerTypAVPTest {
    byte[] buffer = HexFormat.of().parseHex("000001164000000c5416d235");

    @Test
    public void originStateId() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer, 0);
        OriginStateId originStateId = new OriginStateId(header, buffer, 0);
        assertThat(header.getAvpLength()).isEqualTo(12);
        assertThat(header.getAvpFlags()).isEqualTo((byte) 0x40);
        assertThat(header.getAvpCode()).isEqualTo(278);
        assertThat(header.getPaddingSize()).isEqualTo(0);
        assertThat(originStateId.getData()).isEqualTo(1410781749);
    }

}