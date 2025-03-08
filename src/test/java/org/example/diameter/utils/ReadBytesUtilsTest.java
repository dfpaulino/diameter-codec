package org.example.diameter.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadBytesUtilsTest {
    @Test
    public void read3BytesToInt() {
        byte[] b = {0x00, 0x10, 0x00, (byte) 0xf0};
        long i = ReadBytesUtils.readNBytesAsInt(b, 1, 3);
        assertThat(i).isEqualTo(1048816);
    }

    @Test
    public void read4BytesToInt() {
        byte[] b = {(byte) 0x7c, 0x10, 0x00, (byte) 0xf0};
        int i = ReadBytesUtils.readNBytesAsInt(b, 0, 4);
        assertThat(i).isEqualTo((int) 2081423600);

    }

}