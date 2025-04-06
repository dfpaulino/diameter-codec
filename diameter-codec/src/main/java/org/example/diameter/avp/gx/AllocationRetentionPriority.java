package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;

@AvpRegister(avpCode =1034,avpBuilderMethod = "byteToAvp")
public class AllocationRetentionPriority extends Avp<AllocationRetentionPriority> {
    public static int avpCode = 1034;
    public static byte flags = (byte) 0xc0;
    @InnerAvp
    @Setter
    @Getter
    private PriorityLevel priorityLevel;
    @InnerAvp@Getter@Setter
    private PreEmptionCapability preEmptionCapability;
    @InnerAvp@Getter@Setter
    private PreEmptionVulnerability preEmptionVulnerability;

    public AllocationRetentionPriority(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public AllocationRetentionPriority() {
        super(null);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(AllocationRetentionPriority::new);
    }

    @Override
    public AllocationRetentionPriority returnContent() {
        return this;
    }

    @Override
    public AllocationRetentionPriority decode(byte[] buffer, int position, AvpHeader header) {
        return (AllocationRetentionPriority) AvpTypeDecoders.GroupedAvpDecoder
                .decode(this,buffer,position,header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags, VendorId.GPP.getValue());
    }

}
