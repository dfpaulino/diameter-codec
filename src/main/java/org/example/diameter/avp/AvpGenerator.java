package org.example.diameter.avp;

@FunctionalInterface
public interface AvpGenerator<T extends Avp> {
    T createAvp(AvpHeader header, byte[] buf, int pos);
}
