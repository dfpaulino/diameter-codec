package org.example.pcc.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class SessionRule {
    private ApnAggregatedBitRate ambr;
    private AuthorizedDefaultQos authorizedDefaultQos;
}
