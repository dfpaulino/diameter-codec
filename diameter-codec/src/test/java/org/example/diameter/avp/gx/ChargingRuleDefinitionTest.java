package org.example.diameter.avp.gx;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.common.RatingGroup;
import org.example.diameter.avp.common.ServiceIdentifier;
import org.example.diameter.avp.enums.MeteringMethodType;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChargingRuleDefinitionTest {

    private static byte[] bufferOrig = HexFormat.of().parseHex("000003ebc00001f4000028af000003edc000001f000028af5043433130342d514349392d44594e414d494300000001b74000000c00000034000001b04000000c0000000200000422c0000074000028af000001fbc0000046000028af7065726d697420696e2031372066726f6d203137322e31372e3234312e32353520313731303420746f203137322e31362e32302e3131312f33320000000003f6c000000e000028af68fc000000000438c0000010000028af0000000200000422c0000074000028af000001fbc0000047000028af7065726d6974206f75742031372066726f6d203137322e31362e32302e3131312f333220746f203137322e31372e3234312e32353520313731303400000003f6c000000e000028af68fc000000000438c0000010000028af00000001000001ffc0000010000028af00000002000003f8c0000078000028af00000404c0000010000028af0000000900000204c0000010000028af00003e8000000203c0000010000028af00002fa80000040ac000003c000028af00000416c0000010000028af0000000f00000417c0000010000028af0000000100000418c0000010000028af00000001000003efc0000010000028af00000001000003f2c0000010000028af00000001000003f1c0000010000028af00000001000003f0c0000010000028af00000000");
    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(bufferOrig,0);
        ChargingRuleDefinition crd = new ChargingRuleDefinition(header,bufferOrig,0);
        assertThat(crd.getData().getFlowInformation().size()).isEqualTo(2);
    }

    @Test
    void encode() {

        ChargingRuleDefinition crd1 = new ChargingRuleDefinition();
        crd1.setChargingRuleName(new ChargingRuleName("PCC104-QCI9-DYNAMIC"));
        crd1.setServiceIdentifier(new ServiceIdentifier(52));
        crd1.setRatingGroup(new RatingGroup(2));
        crd1.setPrecedence(new Precedence(1));
        crd1.setMeteringMethod(new MeteringMethod(MeteringMethodType.VOLUME));
        crd1.setOnline(new Online(1));
        crd1.setOffline(new Offline(0));
        QoSInformation qos1 = new QoSInformation();
        qos1.setQoSClassIdentifier(new QoSClassIdentifier(9));
        qos1.setMaxRequestedBandwidthUL(new MaxRequestedBandwidthUL(16000));
        qos1.setMaxRequestedBandwidthDL(new MaxRequestedBandwidthDL(12200));
        AllocationRetentionPriority arp = new AllocationRetentionPriority();
        arp.setPriorityLevel(new PriorityLevel(15));
        arp.setPreEmptionCapability(new PreEmptionCapability(1));
        arp.setPreEmptionVulnerability(new PreEmptionVulnerability(1));
        qos1.setAllocationRetentionPriority(arp);
        crd1.setQoSInformation(qos1);

        FlowInformation flowInformation1 = new FlowInformation();
        flowInformation1.setFlowDescription(new FlowDescription("permit in 17 from 172.17.241.255 17104 to 172.16.20.111/32"));
        flowInformation1.setFlowDirection(new FlowDirection(2));
        flowInformation1.setTosTrafficClass(new TosTrafficClass(HexFormat.of().parseHex("68fc")));

        FlowInformation flowInformation2 = new FlowInformation();
        flowInformation2.setFlowDescription(new FlowDescription("permit out 17 from 172.16.20.111/32 to 172.17.241.255 17104"));
        flowInformation2.setFlowDirection(new FlowDirection(1));
        flowInformation2.setTosTrafficClass(new TosTrafficClass(HexFormat.of().parseHex("68fc")));

        crd1.setFlowInformation(flowInformation1);
        crd1.setFlowInformation(flowInformation2);

        crd1.setFlowStatus(new FlowStatus(2));
        byte[] bytes = crd1.encode();
        assertThat(bytes).isEqualTo(bufferOrig);
    }

}