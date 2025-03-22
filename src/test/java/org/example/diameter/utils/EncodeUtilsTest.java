package org.example.diameter.utils;

import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EncodeUtilsTest {

    @Test
    void encodeIntToNBytes() {
        int value = Integer.MAX_VALUE;
        byte[] buffer =EncodeUtils.encodeIntToNBytes(value,4);
        assertThat(HexFormat.of().formatHex(buffer)).isEqualTo("7fffffff");
    }

    @Test
    void encodeIntToNBytes_2() {
        int value = 1024;
        byte[] buffer =EncodeUtils.encodeIntToNBytes(value,3);
        assertThat(HexFormat.of().formatHex(buffer)).isEqualTo("000400");
    }
}