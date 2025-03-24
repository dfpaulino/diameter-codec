package org.example.diameter.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;
import java.util.stream.Collectors;

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

    public static byte[] AddressToBytes(String ip, int family) {

        if(family==1) {
            //ip v4
            byte[] buffer = new byte[2+4+2];

            List<Integer> ipBytes = Arrays.stream(ip.split("\\.")).map(Integer::valueOf).toList();
            buffer[0]=0x00;
            buffer[1]=0x01;
            for(int i = 0;i<4;i++){
                buffer[2+i]= (byte)(ipBytes.get(i)&0xff);
            }
            //padding
            buffer[6]=0x00;
            buffer[7]=0x00;

            int padding = 2;
            return buffer;
        }else {
            //ip v6
            byte[] buffer = new byte[2+16+2];
            List<String> ipStr = Arrays.stream(ip.split(":")).toList();

            buffer[0]=0x00;
            buffer[1]=0x02;
            for(int i = 0;i< ipStr.size();i++){
                String s;
                switch (ipStr.get(i).length()) {
                    case 0:
                        s = "0000";break;
                    case 1:
                        s = "000"+ipStr.get(i);break;
                    case 2:
                        s = "00"+ipStr.get(i);break;
                    case 3:
                        s = "0"+ipStr.get(i);break;
                    default:
                        s = ipStr.get(i);
                }
                byte[] bytesAux = HexFormat.of().parseHex(s);
                buffer[2+i*2]=bytesAux[0];
                buffer[2+i*2 +1]=bytesAux[1];
            }
            //padding
            buffer[18]=0x00;
            buffer[19]=0x00;
            return buffer;
        }

    }


    public static byte[] encodeAddressToBytes(short ipFamily,String ip) {
        return new byte[0];
    }
}
