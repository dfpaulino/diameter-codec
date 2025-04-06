package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.avp.common.SubscriptionId;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

@AvpRegister(avpCode = 1016,avpBuilderMethod = "byteToAvp")
public class QoSInformation extends Avp<QoSInformation> {
    public static int avpCode = 1016;
    public static byte flags = (byte) 0xc0;
    @InnerAvp@Getter@Setter
    private QoSClassIdentifier qoSClassIdentifier;
    @InnerAvp@Getter@Setter
    private MaxRequestedBandwidthUL maxRequestedBandwidthUL;
    @InnerAvp@Getter@Setter
    private MaxRequestedBandwidthDL maxRequestedBandwidthDL;
    @InnerAvp@Getter@Setter
    private GuaranteedBitrateUL guaranteedBitrateUL;
    @InnerAvp@Getter@Setter
    private GuaranteedBitrateDL guaranteedBitrateDL;
    @InnerAvp@Getter@Setter
    private BearerIdentifier bearerIdentifier;
    @InnerAvp@Getter@Setter
    private AllocationRetentionPriority allocationRetentionPriority;
    @InnerAvp@Getter@Setter
    private ApnAggregateMaxBitrateUL apnAggregateMaxBitrateUL;
    @InnerAvp@Getter@Setter
    private ApnAggregateMaxBitrateDL apnAggregateMaxBitrateDL;


    public QoSInformation(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public QoSInformation() {
        super(null);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(QoSInformation::new);
    }

    @Override
    public QoSInformation returnContent() {
        return this;
    }
    @Override
    public QoSInformation decode(byte[] buffer, int position, AvpHeader header) {
        return (QoSInformation) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags, VendorId.GPP.getValue());
    }

}
