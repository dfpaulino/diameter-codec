package org.example.diameter.avp.gx;

import ch.qos.logback.core.model.processor.DenyAllModelFilter;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DefaultEpsBearerQoSTest {
    private static final byte[] buffer = HexFormat.of().parseHex("00000419c0000058000028af00000404c0000010000028af000000090000040ac000003c000028af00000416c0000010000028af0000000900000417c0000010000028af0000000000000418c0000010000028af00000000");

    @Test
    void byteToAvp() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);

        assertThat(header.getAvpCode()).isEqualTo(DefaultEpsBearerQoS.avpCode);
        assertThat(header.getAvpFlags()).isEqualTo(DefaultEpsBearerQoS.flags);
        assertThat(header.getVendorId().get()).isEqualTo(VendorId.GPP.getValue());
        DefaultEpsBearerQoS defaultEpsBearerQoS = new DefaultEpsBearerQoS(header,buffer,0);
        assertThat(defaultEpsBearerQoS.getData().getQoSClassIdentifier().getData()).isEqualTo(9);
        assertThat(defaultEpsBearerQoS.getData().getAllocationRetentionPriority().getData().getPriorityLevel().getData()).isEqualTo(9);
        assertThat(defaultEpsBearerQoS.getData().getAllocationRetentionPriority().getData().getPreEmptionCapability().getData()).isEqualTo(0);
        assertThat(defaultEpsBearerQoS.getData().getAllocationRetentionPriority().getData().getPreEmptionVulnerability().getData()).isEqualTo(0);
    }

    @Test
    void encode() {

        DefaultEpsBearerQoS defaultEpsBearerQoS = new DefaultEpsBearerQoS();
        defaultEpsBearerQoS.setQoSClassIdentifier(new QoSClassIdentifier(9));
        AllocationRetentionPriority arp = new AllocationRetentionPriority();
        arp.setPriorityLevel(new PriorityLevel(9));
        arp.setPreEmptionCapability(new PreEmptionCapability(0));
        arp.setPreEmptionVulnerability(new PreEmptionVulnerability(0));
        defaultEpsBearerQoS.setAllocationRetentionPriority(arp);

        //this is not really accurate, reflection does not guarantee same order of the defined fields...
        assertThat(defaultEpsBearerQoS.encode()).isEqualTo(buffer);
    }
}