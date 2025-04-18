package org.example.diameter.avp.enums;

import java.util.HashMap;
import java.util.Map;

public enum AuthApplicationIdEnum {
    TGPP_GX(16777238),
    TGPP_RX(16777236);
    private int value;
    private static Map<Integer,AuthApplicationIdEnum> map = new HashMap<>();

    AuthApplicationIdEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AuthApplicationIdEnum of(int value){
        return map.get(value);
    }

    static {
        for(AuthApplicationIdEnum e:AuthApplicationIdEnum.values()){
            map.put(e.value,e);
        }

    }
}
