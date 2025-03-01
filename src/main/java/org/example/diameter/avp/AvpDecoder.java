package org.example.diameter.avp;

@FunctionalInterface
public interface AvpDecoder<T> {
    T decode(byte[] buffer, int position, AvpHeader avpHeader);
}
