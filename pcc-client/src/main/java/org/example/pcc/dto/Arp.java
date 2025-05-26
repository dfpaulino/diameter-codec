package org.example.pcc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Arp {
    private long priorityLevel;
    private String preemptCap;
    private String preemptVuln;
}
