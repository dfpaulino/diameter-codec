package org.example.pcc.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FlowInformation {
    private String flowDirection;
    private String flowDescription;
}
