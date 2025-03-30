package org.example.diameter.avp.gx;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.common.RatingGroup;
import org.example.diameter.avp.common.ServiceIdentifier;
import org.example.diameter.avp.enums.MeteringMethodType;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ChargingRuleInstallTest {
    @Test
    public void chargingRuleInstall_ruleBaseName_decode() {
        byte[] buffer = HexFormat.of().parseHex("000003e9c000001c000028af000003ecc000000f000028af31303000");
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);
        ChargingRuleInstall cri = new ChargingRuleInstall(header,buffer,0);

        assertThat(header.getAvpCode()).isEqualTo(ChargingRuleInstall.avpCode);
        assertThat(header.getAvpFlags()).isEqualTo(ChargingRuleInstall.flags);
        assertThat(header.getAvpLength()).isEqualTo(28);
        assertThat(header.getPaddingSize()).isEqualTo(0);

        assertThat(cri.getData().getChargingRuleBaseName().size()).isEqualTo(1);
        assertThat(cri.getData().getChargingRuleBaseName().getFirst().getData()).isEqualTo("100");

        assertThat(cri.getData().getChargingRuleBaseName().getFirst().getHeader().getPaddingSize()).isEqualTo(1);
        assertThat(cri.getData().getChargingRuleBaseName().getFirst().getHeader().getAvpCode()).isEqualTo(ChargingRuleBaseName.avpCode);

    }

    @Test
    public void encode_chargingRuleInstall_ruleBaseName(){
        ChargingRuleInstall cri = new ChargingRuleInstall();
        ChargingRuleBaseName crbn = new ChargingRuleBaseName("100");
        cri.setChargingRuleBaseName(crbn);

        byte[] encoded = cri.encode();
        byte[] buffer = HexFormat.of().parseHex("000003e9c000001c000028af000003ecc000000f000028af31303000");

        assertThat(encoded).isEqualTo(buffer);

        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(encoded,0);
        ChargingRuleInstall cri2 = new ChargingRuleInstall(header,encoded,0);
        assertThat(cri2.getData().getChargingRuleBaseName().getFirst().getData()).isEqualTo("100");


    }

    @Test
    public void chargingRuleInstall_ruleDefinition_decode() {
        //diameter.Session-Id == "string;459;844;IMSI999991234567810"
        byte[] buffer = HexFormat.of().parseHex("000003e9c0000424000028af000003ebc000020c000028af000003edc000001f000028af5043433130342d514349392d44594e414d494300000001b74000000c00000034000001b04000000c0000000200000422c0000074000028af000001fbc0000046000028af7065726d697420696e2031372066726f6d203137322e31372e3234312e32353520313731303420746f203137322e31362e32302e3131312f33320000000003f6c000000e000028af68fc000000000438c0000010000028af0000000200000422c0000074000028af000001fbc0000047000028af7065726d6974206f75742031372066726f6d203137322e31362e32302e3131312f333220746f203137322e31372e3234312e32353520313731303400000003f6c000000e000028af68fc000000000438c0000010000028af00000001000001ffc0000010000028af00000002000003f8c0000078000028af00000404c0000010000028af0000000900000204c0000010000028af00003e8000000203c0000010000028af00002fa80000040ac000003c000028af00000416c0000010000028af0000000f00000417c0000010000028af0000000100000418c0000010000028af00000001000003efc0000010000028af00000001000003f2c0000010000028af00000001000003f1c0000010000028af00000001000003f0c0000010000028af00000000000001b2400000180000043e80000010000028af00000000000003ebc000020c000028af000003edc000001f000028af5043433130352d514349392d44594e414d494300000001b74000000c00000035000001b04000000c0000000300000422c0000074000028af000001fbc0000046000028af7065726d697420696e2031372066726f6d203137322e31372e3234312e32353520313731303520746f203137322e31362e32302e3131312f33320000000003f6c000000e000028af68fc000000000438c0000010000028af0000000200000422c0000074000028af000001fbc0000047000028af7065726d6974206f75742031372066726f6d203137322e31362e32302e3131312f333220746f203137322e31372e3234312e32353520313731303500000003f6c000000e000028af68fc000000000438c0000010000028af00000001000001ffc0000010000028af00000002000003f8c0000078000028af00000404c0000010000028af0000000900000204c0000010000028af00003e8000000203c0000010000028af00002fa80000040ac000003c000028af00000416c0000010000028af0000000f00000417c0000010000028af0000000100000418c0000010000028af00000001000003efc0000010000028af00000001000003f2c0000010000028af00000001000003f1c0000010000028af00000001000003f0c0000010000028af00000000000001b2400000180000043e80000010000028af00000000");
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);
        ChargingRuleInstall cri = new ChargingRuleInstall(header,buffer,0);

        assertThat(header.getAvpCode()).isEqualTo(ChargingRuleInstall.avpCode);
        assertThat(header.getAvpFlags()).isEqualTo(ChargingRuleInstall.flags);
        assertThat(header.getAvpLength()).isEqualTo(1060);
        assertThat(header.getPaddingSize()).isEqualTo(0);

        assertThat(cri.getData().getChargingRuleDefinition().size()).isEqualTo(2);
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getChargingRuleName().getData()).isEqualTo("PCC104-QCI9-DYNAMIC");
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getServiceIdentifier().getData()).isEqualTo(52);
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getRatingGroup().getData()).isEqualTo(2);
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getQoSInformation().getData()).isNotNull();
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getMeteringMethod().getData().getType()).isEqualTo(1);
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getPrecedence().getData()).isEqualTo(1);
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getOffline().getData()).isEqualTo(0);

        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getOnline().getData()).isEqualTo(1);

        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getFlowInformation().size()).isEqualTo(2);
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getFlowInformation().get(0).getData().getFlowDirection().getData()).isEqualTo(2);
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getFlowInformation().get(1).getData().getFlowDirection().getData()).isEqualTo(1);
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getFlowInformation().get(0).getData().getFlowDescription().getData()).isEqualTo("permit in 17 from 172.17.241.255 17104 to 172.16.20.111/32");
        assertThat(cri.getData().getChargingRuleDefinition().getFirst().getData().getFlowInformation().get(1).getData().getFlowDescription().getData()).isEqualTo("permit out 17 from 172.16.20.111/32 to 172.17.241.255 17104");
        assertThat(cri.getData().getChargingRuleDefinition().get(1).getData().getFlowInformation().get(0).getData().getFlowDescription().getData()).isEqualTo("permit in 17 from 172.17.241.255 17105 to 172.16.20.111/32");
        assertThat(cri.getData().getChargingRuleDefinition().get(1).getData().getFlowInformation().get(1).getData().getFlowDescription().getData()).isEqualTo("permit out 17 from 172.16.20.111/32 to 172.17.241.255 17105");

    }

    @Test
    public void chargingRuleInstall_ruleDefinition_encode() {
        //diameter.Session-Id == "string;459;844;IMSI999991234567810"
        ChargingRuleInstall cri = new ChargingRuleInstall();

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

        cri.setChargingRuleDefinition(crd1);

        ChargingRuleDefinition crd2 = new ChargingRuleDefinition();
        crd2.setChargingRuleName(new ChargingRuleName("PCC105-QCI9-DYNAMIC"));
        crd2.setServiceIdentifier(new ServiceIdentifier(53));
        crd2.setRatingGroup(new RatingGroup(3));
        crd2.setPrecedence(new Precedence(1));
        crd2.setMeteringMethod(new MeteringMethod(MeteringMethodType.VOLUME));
        crd2.setOnline(new Online(1));
        crd2.setOffline(new Offline(0));
        QoSInformation qos2 = new QoSInformation();
        qos2.setQoSClassIdentifier(new QoSClassIdentifier(9));
        qos2.setMaxRequestedBandwidthUL(new MaxRequestedBandwidthUL(16000));
        qos2.setMaxRequestedBandwidthDL(new MaxRequestedBandwidthDL(12200));
        AllocationRetentionPriority arp2 = new AllocationRetentionPriority();
        arp2.setPriorityLevel(new PriorityLevel(15));
        arp2.setPreEmptionCapability(new PreEmptionCapability(1));
        arp2.setPreEmptionVulnerability(new PreEmptionVulnerability(1));
        qos2.setAllocationRetentionPriority(arp);
        crd2.setQoSInformation(qos2);

        cri.setChargingRuleDefinition(crd2);

        byte[] encoded =cri.encode();

        assertEncoded(encoded,cri);

    }

    private void assertEncoded(byte[] actual,ChargingRuleInstall criExpected) {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(actual,0);
        assertThat(header.getAvpCode()).isEqualTo(1001);
        assertThat(actual.length).isEqualTo(header.getAvpLength());

        // decode avp
        ChargingRuleInstall cri = new ChargingRuleInstall(header,actual,0);
        assertThat(cri.getData().getChargingRuleDefinition().size()).isEqualTo(2);

        for (int i=0;i<cri.getData().getChargingRuleDefinition().size();i++){

            ChargingRuleDefinition crd1 =cri.getData().getChargingRuleDefinition().get(i).getData();

            assertThat(crd1.getChargingRuleName().getData())
                    .isEqualTo(criExpected.getChargingRuleDefinition().get(i).getChargingRuleName().getData());
            assertThat(crd1.getOnline().getData())
                    .isEqualTo(criExpected.getChargingRuleDefinition().get(i).getOnline().getData());
            assertThat(crd1.getOffline().getData())
                    .isEqualTo(criExpected.getChargingRuleDefinition().get(i).getOffline().getData());
            assertThat(crd1.getRatingGroup().getData())
                    .isEqualTo(criExpected.getChargingRuleDefinition().get(i).getRatingGroup().getData());
            assertThat(crd1.getServiceIdentifier()
                    .getData()).isEqualTo(criExpected.getChargingRuleDefinition().get(i).getServiceIdentifier().getData());


            QoSInformation qoSInformation = crd1.getQoSInformation().getData();
            assertThat(qoSInformation.getMaxRequestedBandwidthDL().getData())
                    .isEqualTo(criExpected.getChargingRuleDefinition().get(i).getQoSInformation().getMaxRequestedBandwidthDL().getData());
            assertThat(qoSInformation.getMaxRequestedBandwidthUL().getData())
                    .isEqualTo(criExpected.getChargingRuleDefinition().get(i).getQoSInformation().getMaxRequestedBandwidthUL().getData());

            assertThat(qoSInformation.getAllocationRetentionPriority().getData().getPriorityLevel().getData())
                    .isEqualTo(criExpected.getChargingRuleDefinition().get(i).getQoSInformation().getAllocationRetentionPriority().getPriorityLevel().getData());
            assertThat(qoSInformation.getAllocationRetentionPriority().getData().getPreEmptionVulnerability().getData())
                    .isEqualTo(criExpected.getChargingRuleDefinition().get(i).getQoSInformation().getAllocationRetentionPriority().getPreEmptionVulnerability().getData());
            assertThat(qoSInformation.getAllocationRetentionPriority().getData().getPreEmptionCapability().getData())
                    .isEqualTo(criExpected.getChargingRuleDefinition().get(i).getQoSInformation().getAllocationRetentionPriority().getPreEmptionCapability().getData());



        }

    }

}