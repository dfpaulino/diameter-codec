package org.example.diameter.avp.gx;

import lombok.Getter;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class QoSInformation extends Avp<QoSInformation> {
    public static int avpCode = 1016;
    public static byte flags = (byte) 0xc0;
    @Getter
    private QoSClassIdentifier qoSClassIdentifier;
    @Getter
    private MaxRequestedBandwidthUL maxRequestedBandwidthUL;
    @Getter
    private MaxRequestedBandwidthDL maxRequestedBandwidthDL;
    @Getter
    private GuaranteedBitrateUL guaranteedBitrateUL;
    @Getter
    private GuaranteedBitrateDL guaranteedBitrateDL;
    @Getter
    private BearerIdentifier bearerIdentifier;
    @Getter
    private AllocationRetentionPriority allocationRetentionPriority;
    @Getter
    private ApnAggregateMaxBitrateUL apnAggregateMaxBitrateUL;
    @Getter
    private ApnAggregateMaxBitrateDL apnAggregateMaxBitrateDL;


    private void setQoSClassIdentifier(QoSClassIdentifier qoSClassIdentifier) {
        this.qoSClassIdentifier = qoSClassIdentifier;
    }

    private void setMaxRequestedBandwidthUL(MaxRequestedBandwidthUL maxRequestedBandwidthUL) {
        this.maxRequestedBandwidthUL = maxRequestedBandwidthUL;
    }

    private void setMaxRequestedBandwidthDL(MaxRequestedBandwidthDL maxRequestedBandwidthDL) {
        this.maxRequestedBandwidthDL = maxRequestedBandwidthDL;
    }

    private void setGuaranteedBitrateUL(GuaranteedBitrateUL guaranteedBitrateUL) {
        this.guaranteedBitrateUL = guaranteedBitrateUL;
    }

    private void setGuaranteedBitrateDL(GuaranteedBitrateDL guaranteedBitrateDL) {
        this.guaranteedBitrateDL = guaranteedBitrateDL;
    }

    private void setBearerIdentifier(BearerIdentifier bearerIdentifier) {
        this.bearerIdentifier = bearerIdentifier;
    }

    private void setAllocationRetentionPriority(AllocationRetentionPriority allocationRetentionPriority) {
        this.allocationRetentionPriority = allocationRetentionPriority;
    }

    private void setApnAggregateMaxBitrateUL(ApnAggregateMaxBitrateUL apnAggregateMaxBitrateUL) {
        this.apnAggregateMaxBitrateUL = apnAggregateMaxBitrateUL;
    }

    private void setApnAggregateMaxBitrateDL(ApnAggregateMaxBitrateDL apnAggregateMaxBitrateDL) {
        this.apnAggregateMaxBitrateDL = apnAggregateMaxBitrateDL;
    }

    public QoSInformation(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public QoSInformation decode(byte[] buffer, int position, AvpHeader header) {
        return (QoSInformation) AvpDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

}
