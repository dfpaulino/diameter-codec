package org.example.diameter.utils;

import java.nio.charset.StandardCharsets;

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
    OctetString must be aware of padding.
    encoded bytes must be aligned on the 32 bits (4 bytes) boundary.
     */
    public static byte[] OctectStringUTF8ToBytes(String s) {
        int padding = (s.length()%4==0?0:(4-s.length()%4));

        byte[] bytes = new byte[s.length() + padding];
        System.arraycopy(s.getBytes(StandardCharsets.UTF_8),0,bytes,0,s.length());
        // add padding for te remaining bytes
        for (int i=s.length();i < bytes.length;i++){
            bytes[i] = 0x00;
        }
        return bytes;
    }


    public static byte[] encodeAddressToBytes(short ipFamily,String ip) {
        return new byte[0];
    }
}
