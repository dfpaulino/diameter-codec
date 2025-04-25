package org.example.pcc.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Arp {
    private long priorityLevel;
    private String preemptCap;
    private String preemptVuln;
}
