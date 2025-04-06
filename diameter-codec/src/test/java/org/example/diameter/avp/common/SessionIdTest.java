package org.example.diameter.avp.common;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionIdTest {
    private static byte[] ORIGINAL_BUFFER = HexFormat.of().parseHex("000001074000002a737472696e673b3435393b3834343b494d53493939393939313233343536373831300000");

    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(ORIGINAL_BUFFER,0);
        SessionId sessionId = new SessionId(header, ORIGINAL_BUFFER,0);
        assertThat(header.getAvpLength()).isEqualTo(42);
        assertThat(header.getPaddingSize()).isEqualTo(2);
        assertThat(header.getAvpCode()).isEqualTo(263);
        assertThat(header.isVendorSpecific()).isFalse();
        assertThat(header.getAvpFlags()).isEqualTo((byte)0x40);
        assertThat(sessionId.getData()).isEqualTo("string;459;844;IMSI999991234567810");
    }

    @Test
    void encode() {
        // we must get to the original buffer
        SessionId sessionId = new SessionId("string;459;844;IMSI999991234567810");
        byte[] actual = sessionId.encode();
        assertThat(actual).isEqualTo(ORIGINAL_BUFFER);
        //System.out.println(sessionId.encode());
    }
}