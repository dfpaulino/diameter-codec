package org.example.diameter.avp;

@FunctionalInterface
public interface GroupedAvpDecoder<T extends Avp> {
    T decode(T self, byte[] buffer, int position, AvpHeader avpHeader);
}
