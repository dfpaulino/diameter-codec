package org.example.diameter.avp.common;

import org.example.diameter.Utils.ReadAvpHeader;
import org.example.diameter.Utils.ReadBytesUtils;
import org.example.diameter.avp.AvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StringTypAVPTest {
    byte[] buffer = HexFormat.of().parseHex("0000010840000031707473642d352e6d6f64756c652d312e54504550545330312e74616977616e6d6f62696c652e636f6d000000");

    @Test
    public void originHostTest_decode() {
        int avpCode = ReadBytesUtils.readNBytesAsInt(buffer, 0, 4);
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer, 0);
        OriginHost originHost = new OriginHost(header, buffer, 0);

        assertThat(originHost.getData()).isEqualTo("ptsd-5.module-1.TPEPTS01.taiwanmobile.com");
        assertThat(header.getAvpLength()).isEqualTo(49);
        assertThat(header.getAvpFlags()).isEqualTo((byte) 0x40);
        assertThat(header.getAvpCode()).isEqualTo(264);
        assertThat(header.getPaddingSize()).isEqualTo(3);
    }
}