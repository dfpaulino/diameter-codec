package org.example.diameter.avp.gx;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AllocationRetentionPriorityTest {
private static byte[] buffer = HexFormat.of().parseHex("0000040ac000003c000028af00000416c0000010000028af0000000900000417c0000010000028af0000000000000418c0000010000028af00000000");
    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);
        AllocationRetentionPriority allocationRetentionPriority = new AllocationRetentionPriority(header,buffer,0);

        allocationRetentionPriority.getData();
        assertThat(allocationRetentionPriority.getData().getPriorityLevel().getData()).isEqualTo(9);
    }

    @Test
    void encode() {
        AllocationRetentionPriority arp = new AllocationRetentionPriority();
        arp.setPriorityLevel(new PriorityLevel(9));
        arp.setPreEmptionCapability(new PreEmptionCapability(0));
        arp.setPreEmptionVulnerability(new PreEmptionVulnerability(0));
        byte[] encode = arp.encode();

        assertThat(encode).isEqualTo(buffer);

        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(encode,0);
        AllocationRetentionPriority allocationRetentionPriority = new AllocationRetentionPriority(header,encode,0);

        assertThat(allocationRetentionPriority.getData().getPriorityLevel().getData()).isEqualTo(9);
        assertThat(allocationRetentionPriority.getData().getPreEmptionCapability().getData()).isEqualTo(0);
        assertThat(allocationRetentionPriority.getData().getPreEmptionVulnerability().getData()).isEqualTo(0);


    }
}