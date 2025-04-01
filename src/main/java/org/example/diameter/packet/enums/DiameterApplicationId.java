package org.example.diameter.packet.enums;

import java.util.HashMap;
import java.util.Map;

public enum DiameterApplicationId {
    _3GPP(16777238);

    private final int value;
    private static Map<Integer, DiameterApplicationId> map = new HashMap<>();

    DiameterApplicationId(int id) {
        this.value = id;
    }

    public int getValue() {
        return value;
    }

    public static DiameterApplicationId of(int value){
        return map.get(value);
    }

    static {
        for(DiameterApplicationId e:DiameterApplicationId.values()){
            map.put(e.value,e);
        }

    }
}
