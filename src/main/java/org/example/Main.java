package org.example;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.printf("positition {%s}|limit {%s}%n", buffer.position(), buffer.limit());
        byte[] x = {0x10, 0x31, 0x10, 0x31, 0x10, 0x31, 0x10, 0x31,};
        //byte[] x2 = {0x12,0x13};
        buffer.put(x);
        System.out.printf("positition {%s}|limit {%s}%n", buffer.position(), buffer.limit());
        System.out.println("adding + 2 byytes");
        //buffer.put(x2);
        System.out.printf("positition {%s}|limit {%s}%n", buffer.position(), buffer.limit());

        buffer.flip();

        System.out.println();
        buffer.put((byte) 0xff);
        System.out.println();

    }
}