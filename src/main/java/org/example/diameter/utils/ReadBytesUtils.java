package org.example.diameter.utils;

import java.util.Arrays;

public class ReadBytesUtils {
    public static int readNBytesAsInt(byte[] buffer, int position, int len) {

        long value = 0;
        int i = 0;
        while (i < len) {
            value |= (buffer[position + i] & 0xFF) << (8 * (len - i - 1));
            i++;
        }
        return (int) value;
    }

    public static long readNBytesAsLong(byte[] buffer, int position, int len) {

        long value = 0;
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
