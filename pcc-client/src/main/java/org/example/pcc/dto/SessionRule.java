package org.example.pcc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.immutables.value.Value;
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionRule {
    private   ApnAggregatedBitRate ambr;
    private  AuthorizedDefaultQos authorizedDefaultQos;
}
