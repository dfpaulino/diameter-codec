package org.example.pcc.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class QosData {
    private long ambrUpload;
    private long ambrDownload;
    private long maxBrUpload;
    private long maxBrDownload;
}
