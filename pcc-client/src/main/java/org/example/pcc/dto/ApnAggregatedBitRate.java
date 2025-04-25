package org.example.pcc.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ApnAggregatedBitRate {
    private  int uplink;
    private  int downlink;
}
