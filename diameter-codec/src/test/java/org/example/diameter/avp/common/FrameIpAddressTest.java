package org.example.diameter.avp.common;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FrameIpAddressTest {
    byte[] buffer = HexFormat.of().parseHex("000000084000000cc0a8ca1d");

    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer, 0);
        FrameIpAddress frameIpAddress = new FrameIpAddress(header, buffer, 0);
        byte[] ip = frameIpAddress.getData();

        byte[] expected = HexFormat.of().parseHex("c0a8ca1d");
        assertThat(header.getAvpCode()).isEqualTo(FrameIpAddress.avpCode);
        assertThat(header.getAvpFlags()).isEqualTo(FrameIpAddress.flags);
        assertThat(ip).isEqualTo(expected);
        assertThat(ip.length).isEqualTo(4);
        String ipStr = (ip[0] & 0xff) + "." + (ip[1] & 0xff) + "." + (ip[2] & 0xff) + "." + (ip[3] & 0xff);
        assertThat(ipStr).isEqualTo("192.168.202.29");
    }
}