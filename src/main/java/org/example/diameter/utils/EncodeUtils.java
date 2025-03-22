package org.example.diameter.utils;

public class EncodeUtils {

    /*
    Integers are encoded into 4 octets
    use big endian
     */
    public static byte[] encodeIntTo4Bytes(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((i>>24)&0xff);
        bytes[1] = (byte)((i>>16)&0xff);
        bytes[2] = (byte)((i>>8)&0xff);
        bytes[3] = (byte)(i&0xff);
        return bytes;
    }

    public static byte[] encodeIntToNBytes(int value,int numberOfBytes) {
        byte[] bytes = new byte[numberOfBytes];
        for(int i =0;i<numberOfBytes;i++){
            bytes[i] = (byte)((value>>(8*(numberOfBytes-i-1))&0xff));
        }
        return bytes;
    }
    /*
    silly... justo to avoid scattering encoders utilities
     */
    public static byte[] encodeStringToBytes(String s) {
        return s.getBytes();
    }

    public static byte[] encodeNtpTimeToBytes(String s) {
        return s.getBytes();
    }
    public static byte[] encodeAddressToBytes(String s) {
        return s.getBytes();
    }
}
