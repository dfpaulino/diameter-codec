package org.example.pcc.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SessionPolicy {
    private Map<String,SessionRule> sessionRules;
    private Map<String,PccRule> pccRules;
    // desc of qoS
    private  Map<String,QosData> qosDecs;
    private List<Integer> policyCtrlReqTriggers;
    private Boolean online;
    private Boolean offline;


}
