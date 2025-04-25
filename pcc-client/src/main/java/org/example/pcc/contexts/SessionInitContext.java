package org.example.pcc.contexts;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SessionInitContext {
    //msisdn
    private String gpsi;
    //imsi
    private String supi;

    private Long pduSessionId;
    //Apn
    private String dnn;
    private int ratType;
    //UE info imei
    private String pei;
    private String ipv4Address;

}
