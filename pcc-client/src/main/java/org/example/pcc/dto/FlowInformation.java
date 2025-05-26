package org.example.pcc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FlowInformation {
    private String flowDirection;
    private String flowDescription;
}
