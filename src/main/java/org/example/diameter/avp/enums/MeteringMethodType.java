package org.example.diameter.avp.enums;

import java.util.HashMap;
import java.util.Map;

public enum MeteringMethodType {
    DURATION(0),
    VOLUME(1),
    DURATION_VOLUME(2),
    EVENT(3);

    private static Map<Integer,MeteringMethodType> map = new HashMap<>();
    private final int type;

    MeteringMethodType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static MeteringMethodType of(int type){
        return map.get(type);
    }

    static {
        for(MeteringMethodType e:MeteringMethodType.values()){
            map.put(e.type,e);
        }

    }
}
