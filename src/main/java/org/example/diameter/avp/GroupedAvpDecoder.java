package org.example.diameter.avp;

@FunctionalInterface
public interface GroupedAvpDecoder<T> {
    T decode(T self, byte[] buffer, int position, AvpHeader avpHeader);
}
