package org.example.pcc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PccRule {
    private String name;
    private List<FlowInformation> flowInfos;
    private int precedence;
    private List<String> refQosData;

}
