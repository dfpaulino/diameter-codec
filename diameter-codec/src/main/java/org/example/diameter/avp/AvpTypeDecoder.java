package org.example.diameter.avp;

@FunctionalInterface
public interface AvpTypeDecoder<T> {
    T decode(byte[] buffer, int position, AvpHeader avpHeader);
}
