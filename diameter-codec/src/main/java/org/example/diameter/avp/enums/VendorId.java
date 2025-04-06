package org.example.diameter.avp.enums;

public enum VendorId {
    GPP(10415);
    private final int value;

    VendorId(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
