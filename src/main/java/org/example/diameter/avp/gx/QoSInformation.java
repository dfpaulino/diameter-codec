package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;

@AvpRegister(avpCode = 1016,avpBuilderMethod = "byteToAvp")
public class QoSInformation extends Avp<QoSInformation> {
    public static int avpCode = 1016;
    public static byte flags = (byte) 0xc0;
    @Getter@Setter
    private QoSClassIdentifier qoSClassIdentifier;
    @Getter@Setter
    private MaxRequestedBandwidthUL maxRequestedBandwidthUL;
    @Getter@Setter
    private MaxRequestedBandwidthDL maxRequestedBandwidthDL;
    @Getter@Setter
    private GuaranteedBitrateUL guaranteedBitrateUL;
    @Getter@Setter
    private GuaranteedBitrateDL guaranteedBitrateDL;
    @Getter@Setter
    private BearerIdentifier bearerIdentifier;
    @Getter@Setter
    private AllocationRetentionPriority allocationRetentionPriority;
    @Getter@Setter
    private ApnAggregateMaxBitrateUL apnAggregateMaxBitrateUL;
    @Getter@Setter
    private ApnAggregateMaxBitrateDL apnAggregateMaxBitrateDL;


    public QoSInformation(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(QoSInformation::new);
    }

    @Override
    public QoSInformation decode(byte[] buffer, int position, AvpHeader header) {
        return (QoSInformation) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

}
