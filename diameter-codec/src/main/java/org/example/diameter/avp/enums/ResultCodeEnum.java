package org.example.diameter.avp.enums;

import java.util.HashMap;
import java.util.Map;

public enum ResultCodeEnum {
    DIAMETER_SUCCESS(2001),
    DIAMETER_COMMAND_UNSUPPORTED(3001),
    DIAMETER_UNABLE_TO_DELIVER(3002),
    DIAMETER_APPLICATION_UNSUPPORTED(3007),
    DIAMETER_UNKNOWN_SESSION_ID(5002),
    DIAMETER_MISSING_AVP(5005),
    DIAMETER_NO_COMMON_APPLICATION(5010),
    DIAMETER_UNSUPPORTED_VERSION(5001);

    private static Map<Integer, ResultCodeEnum> map = new HashMap<>();
    private final int value;


    ResultCodeEnum(int type) {
        this.value = type;
    }

    public static ResultCodeEnum of(int type){
            return map.get(type);
    }

    public int getValue() {
        return value;
    }

    static {
        for(ResultCodeEnum e: ResultCodeEnum.values()){
            map.put(e.value,e);
        }

    }
}
