package org.example.pcc.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class ApnAggregatedBitRate {
    private  int uplink;
    private  int downlink;
}
