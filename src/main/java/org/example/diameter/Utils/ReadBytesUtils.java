package org.example.diameter.Utils;

import java.util.Arrays;

public class ReadBytesUtils {
    public static int readNBytesAsInt(byte[] buffer, int position, int len) {

        int value = 0;
        int i = 0;
        while (i < len) {
            value |= (buffer[position + i] & 0xFF) << (8 * (len - i - 1));
            i++;
        }

        return value;
    }

    public static byte[] readNBytesAsByteArray(byte[] buffer, int position, int len) {

        return Arrays.copyOfRange(buffer, position, position + len);
    }

    public static byte readByteAsByte(byte[] buffer, int position) {
        return (byte) buffer[position];
    }

}
