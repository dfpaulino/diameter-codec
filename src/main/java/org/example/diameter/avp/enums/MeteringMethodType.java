package org.example.diameter.avp.enums;

public enum MeteringMethodType {
    DURATION(0),
    VOLUME(1),
    DURATION_VOLUME(2),
    EVENT(3);

    private final int type;

    MeteringMethodType(int type) {
        this.type = type;
    }
}
