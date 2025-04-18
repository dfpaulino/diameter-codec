package org.example.diameter.packet.enums;

import java.util.HashMap;
import java.util.Map;

public enum DiameterApplicationId {
    COMMON(0),
    _3GPP_GX(16777238),
    _RX(16777236);

    private final long value;
    private static Map<Long, DiameterApplicationId> map = new HashMap<>();

    DiameterApplicationId(int id) {
        this.value = id;
    }

    public long getValue() {
        return value;
    }

    public static DiameterApplicationId of(long value){
        return map.get(value);
    }

    static {
        for(DiameterApplicationId e:DiameterApplicationId.values()){
            map.put(e.value,e);
        }

    }
}
