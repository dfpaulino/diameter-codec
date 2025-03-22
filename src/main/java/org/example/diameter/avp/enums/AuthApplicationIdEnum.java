package org.example.diameter.avp.enums;

public enum AuthApplicationIdEnum {
    TGPP(16777238);
    private int value;

    AuthApplicationIdEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
