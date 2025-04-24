package org.example.diameter.avp.enums;

import java.util.HashMap;
import java.util.Map;

public enum CreditControlTypeEnum {
    INIT(1),
    UPDATE(2),
    TERMINATE(3);
    private int value;
    private static Map<Integer, CreditControlTypeEnum> map = new HashMap<>();

    CreditControlTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CreditControlTypeEnum of(int value){
        return map.get(value);
    }

    static {
        for(CreditControlTypeEnum e: CreditControlTypeEnum.values()){
            map.put(e.value,e);
        }

    }
}
