package org.example.diameter.avp;

public interface AvpGet {
    Avp<?> create(AvpHeader header, byte[] buffer, int position);
}
