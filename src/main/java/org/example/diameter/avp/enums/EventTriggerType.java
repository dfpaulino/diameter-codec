package org.example.diameter.avp.enums;

public enum EventTriggerType {
    SGSN_CHANGE(0),
    QOS_CHANGE(1),
    RAT_CHANGE(2),
    TFT_CHANGE(3),
    PLMN_CHANGE(4),
    IP_CAN_CHANGE(7),
    RAI_CHANGE(12),
    USER_LOCATION_CHANGE(13),
    NO_EVENT_TRIGGERS(14),
    OUT_OF_CREDIT(11),
    REVALIDATION_TIMEOUT(11),
    TAI_CHANGE(26),
    ECGI_CHANGE(27),
    USAGE_REPORT(33),
    UE_LOCAL_IP_ADDRESS_CHANGE(43),
    IGNORE(100);

    private int type;

    EventTriggerType(int type) {
        this.type = type;
    }

    public static EventTriggerType of(int type){
            return IGNORE;

    }
}
