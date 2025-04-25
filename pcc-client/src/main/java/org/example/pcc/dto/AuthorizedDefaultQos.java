package org.example.pcc.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class AuthorizedDefaultQos {
    private Long _5qi;
    private Arp arp;
    private int maxbrUl;
    private int maxbrDl;
    private int gbrUl;
    private int gbrDl;
}
