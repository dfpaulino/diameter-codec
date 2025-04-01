package org.example.diameter.packet.enums;

import java.util.HashMap;
import java.util.Map;

public enum DiameterCmdCode {
    CREDIT_CONTROL(272),
    CAPABILITIES_EXCHANGE(257),
    DWD(280);
    private final int value;
    private static Map<Integer, DiameterCmdCode> map = new HashMap<>();

    DiameterCmdCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DiameterCmdCode of(int value){
        return map.get(value);
    }

    static {
        for(DiameterCmdCode e:DiameterCmdCode.values()){
            map.put(e.value,e);
        }

    }

}
