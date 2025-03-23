package org.example.diameter.utils;

import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Test
    public void encodeOctetString_whenSizeBoundedTo4Octets(){
        String s = "ABCDefgH";
        byte[] encodedBytes = EncodeUtils.OctectStringUTF8ToBytes(s);
        assertThat(encodedBytes.length).isEqualTo(s.length());
    }

    @Test
    public void encodeOctetString_whenSizeNotBoundedTo4Octets(){
        String s = "ABCDefgHI";
        byte[] encodedBytes = EncodeUtils.OctectStringUTF8ToBytes(s);
        assertThat(encodedBytes.length).isEqualTo(s.length()+3);
    }

    @Test
    public void encodeOctetString_whenSizeNotBoundedTo4Octets_2(){
        String s = "ABCDefgHIJ";
        byte[] encodedBytes = EncodeUtils.OctectStringUTF8ToBytes(s);
        assertThat(encodedBytes.length).isEqualTo(s.length()+2);
    }

}