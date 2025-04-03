package org.example.diameter.packet;


import org.example.diameter.avp.common.*;
import org.example.diameter.avp.enums.AuthApplicationIdEnum;
import org.example.diameter.avp.gx.*;
import org.example.diameter.utils.ReadDiameterHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CreditControlAnswerTest {
    //session Id  ptsd-5.module-1.TPEPTS01.taiwanmobile.com;1411018761;0;192.168.202.29
    private byte[] buffer1 = HexFormat.of().parseHex("010001b84000011001000016237350b12375ff9d000001074000004d707473642d352e6d6f64756c652d312e54504550545330312e74616977616e6d6f62696c652e636f6d3b313431313031383736313b303b3139322e3136382e3230322e32390000000000010c4000000c000007d10000010840000028534954435341504330312e736170632e74616977616e6d6f62696c652e636f6d000001284000001d736170632e74616977616e6d6f62696c652e636f6d000000000001024000000c01000016000001a04000000c000000010000019f4000000c00000000000003eec0000010000028af00000001000003eec0000010000028af00000002000003eec0000010000028af00000000000003eec0000010000028af00000021000003e9c000001c000028af000003ecc000000f000028af31303000000003f8c000002c000028af00000203c0000010000028af0927c00000000204c0000010000028af0927c0000000027480000038000028af0000010a4000000c000028af0000027580000010000028af000000010000027680000010000028af0000000b000003ffc0000010000028af00000000000001164000000c0000000d");
    // session id string;459;844;IMSI999991234567810
    private byte[] buffer2 = HexFormat.of().parseHex("010006004000011001000016b60adc87f267af85000001074000002a737472696e673b3435393b3834343b494d53493939393939313233343536373831300000000001024000000c01000016000001084000001b74766d2d76706372662e6d61676d612e636f6d0000000128400000116d61676d612e636f6d0000000000010c4000000c000007d1000001a04000000c000000010000019f4000000c00000000000001160000000c608fad23000003ffc0000010000028af00000002000003eec0000010000028af00000001000003e9c0000424000028af000003ebc000020c000028af000003edc000001f000028af5043433130342d514349392d44594e414d494300000001b74000000c00000034000001b04000000c0000000200000422c0000074000028af000001fbc0000046000028af7065726d697420696e2031372066726f6d203137322e31372e3234312e32353520313731303420746f203137322e31362e32302e3131312f33320000000003f6c000000e000028af68fc000000000438c0000010000028af0000000200000422c0000074000028af000001fbc0000047000028af7065726d6974206f75742031372066726f6d203137322e31362e32302e3131312f333220746f203137322e31372e3234312e32353520313731303400000003f6c000000e000028af68fc000000000438c0000010000028af00000001000001ffc0000010000028af00000002000003f8c0000078000028af00000404c0000010000028af0000000900000204c0000010000028af00003e8000000203c0000010000028af00002fa80000040ac000003c000028af00000416c0000010000028af0000000f00000417c0000010000028af0000000100000418c0000010000028af00000001000003efc0000010000028af00000001000003f2c0000010000028af00000001000003f1c0000010000028af00000001000003f0c0000010000028af00000000000001b2400000180000043e80000010000028af00000000000003ebc000020c000028af000003edc000001f000028af5043433130352d514349392d44594e414d494300000001b74000000c00000035000001b04000000c0000000300000422c0000074000028af000001fbc0000046000028af7065726d697420696e2031372066726f6d203137322e31372e3234312e32353520313731303520746f203137322e31362e32302e3131312f33320000000003f6c000000e000028af68fc000000000438c0000010000028af0000000200000422c0000074000028af000001fbc0000047000028af7065726d6974206f75742031372066726f6d203137322e31362e32302e3131312f333220746f203137322e31372e3234312e32353520313731303500000003f6c000000e000028af68fc000000000438c0000010000028af00000001000001ffc0000010000028af00000002000003f8c0000078000028af00000404c0000010000028af0000000900000204c0000010000028af00003e8000000203c0000010000028af00002fa80000040ac000003c000028af00000416c0000010000028af0000000f00000417c0000010000028af0000000100000418c0000010000028af00000001000003efc0000010000028af00000001000003f2c0000010000028af00000001000003f1c0000010000028af00000001000003f0c0000010000028af00000000000001b2400000180000043e80000010000028af00000000000003f1c0000010000028af00000001000003f0c0000010000028af00000000000003f8c0000098000028af00000404c0000010000028af0000000900000204c0000010000028af00003e8000000203c0000010000028af00002fa80000040ac000003c000028af00000416c0000010000028af0000000900000417c0000010000028af0000000000000418c0000010000028af000000000000041180000010000028af02cd29c00000041080000010000028af05c81a4000000419c0000058000028af00000404c0000010000028af000000090000040ac000003c000028af00000416c0000010000028af0000000900000417c0000010000028af0000000000000418c0000010000028af00000000");
    @Test
    public void decode() {
        DiameterPacketHeader header = ReadDiameterHeader.readDiameterHeaderFromBytes(buffer1);
        CreditControlAnswer cca = new CreditControlAnswer(header,buffer1);

        assertThat(header.getMessageLength()).isEqualTo(buffer1.length);
        assertThat(header.getCommandCode()).isEqualTo(272);
        assertThat(header.getCommandFlags()).isEqualTo((byte)0x40);

        assertThat(cca.getSessionId().getData()).isEqualTo("ptsd-5.module-1.TPEPTS01.taiwanmobile.com;1411018761;0;192.168.202.29");
        assertThat(cca.getAuthApplicationId().getData()).isEqualTo(16777238);
        assertThat(cca.getCcRequestType().getData()).isEqualTo(1);
        assertThat(cca.getCcRequestNumber().getData()).isEqualTo(0);
        assertThat(cca.getEventTrigger().size()).isEqualTo(4);
        assertThat(cca.getEventTrigger().getFirst().getData()).isEqualTo(1);
        assertThat(cca.getEventTrigger().get(1).getData()).isEqualTo(2);
        assertThat(cca.getEventTrigger().get(2).getData()).isEqualTo(0);
        assertThat(cca.getEventTrigger().get(3).getData()).isEqualTo(33);

        assertThat(cca.getChargingRuleInstall().size()).isEqualTo(1);
        assertThat(cca.getChargingRuleInstall().getFirst().getData().getChargingRuleBaseName().getFirst().getData()).isEqualTo("100");
        assertThat(cca.getQoSInformation().getFirst().getData().getMaxRequestedBandwidthDL().getData()).isEqualTo(153600000);
    }

    @Test
    public void decode_buffer2() {
        DiameterPacketHeader header = ReadDiameterHeader.readDiameterHeaderFromBytes(buffer2);
        CreditControlAnswer cca = new CreditControlAnswer(header,buffer2);

        assertThat(header.getMessageLength()).isEqualTo(buffer2.length);
        assertThat(header.getCommandCode()).isEqualTo(272);
        assertThat(header.getCommandFlags()).isEqualTo((byte)0x40);

        assertThat(cca.getSessionId().getData()).isEqualTo("string;459;844;IMSI999991234567810");
        assertThat(cca.getAuthApplicationId().getData()).isEqualTo(16777238);
        assertThat(cca.getCcRequestType().getData()).isEqualTo(1);
        assertThat(cca.getCcRequestNumber().getData()).isEqualTo(0);
        assertThat(cca.getEventTrigger().size()).isEqualTo(1);
        assertThat(cca.getEventTrigger().getFirst().getData()).isEqualTo(1);


        assertThat(cca.getChargingRuleInstall().size()).isEqualTo(1);
        assertThat(cca.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().size()).isEqualTo(2);
        assertThat(cca.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().getFirst().getData().getChargingRuleName().getData()).isEqualTo("PCC104-QCI9-DYNAMIC");
        assertThat(cca.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().getFirst().getData().getServiceIdentifier().getData()).isEqualTo(52);
        assertThat(cca.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().getFirst().getData().getFlowInformation().getFirst().getData().getFlowDirection().getData()).isEqualTo(2);
    }

    @Test
    public void encode() {

        byte[] encoded = generateCca().encode();

        DiameterPacketHeader headeractual = ReadDiameterHeader.readDiameterHeaderFromBytes(encoded);
        CreditControlAnswer ccaActual = new CreditControlAnswer(headeractual,encoded);

        assertThat(ccaActual.getAuthApplicationId().getData()).isEqualTo(AuthApplicationIdEnum.TGPP.getValue());
        assertThat(ccaActual.getSessionId().getData()).isEqualTo("string;459;844;IMSI999991234567810");
        assertThat(ccaActual.getOriginHost().getData()).isEqualTo("pcrf.local");
        assertThat(ccaActual.getOriginRealm().getData()).isEqualTo("home.com");
        assertThat(ccaActual.getEventTrigger().size()).isEqualTo(2);
        assertThat(ccaActual.getResultCode().getData()).isEqualTo(2001);

        assertThat(ccaActual.getChargingRuleInstall().size()).isEqualTo(1);
        assertThat(ccaActual.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().getFirst().getData()
                .getChargingRuleName().getData()).isEqualTo("PCC-100");
        assertThat(ccaActual.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().getFirst().getData()
                .getFlowInformation().size()).isEqualTo(2);
        assertThat(ccaActual.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().getFirst().getData()
                .getFlowInformation().getFirst().getData().getFlowDescription().getData()).isEqualTo("permit out 17 from 172.16.20.111/32 to 172.17.241.255 17104");
        assertThat(ccaActual.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().getFirst().getData()
                .getOnline().getData()).isEqualTo(1);
        assertThat(ccaActual.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().getFirst().getData()
                .getQoSInformation().getData().getMaxRequestedBandwidthDL().getData()).isEqualTo(16000);
        assertThat(ccaActual.getChargingRuleInstall().getFirst().getData().getChargingRuleDefinition().getFirst().getData()
                .getQoSInformation().getData().getMaxRequestedBandwidthUL().getData()).isEqualTo(32000);

        System.out.println("hello");

    }


    private CreditControlAnswer generateCca() {
        DiameterPacketHeader header = ReadDiameterHeader.readDiameterHeaderFromBytes(buffer2);
        CreditControlAnswer ccaBase = new CreditControlAnswer(header,buffer2);


        CreditControlAnswer cca = new CreditControlAnswer();
        cca.setHeader(header);
        cca.setSessionId(new SessionId("string;459;844;IMSI999991234567810"));
        cca.setAuthApplicationId(new AuthApplicationId(AuthApplicationIdEnum.TGPP.getValue()));
        cca.setOriginHost(new OriginHost("pcrf.local"));
        cca.setOriginRealm(new OriginRealm("home.com"));
        cca.setResultCode(new ResultCode(2001));

        cca.setEventTrigger(new EventTrigger(1));
        cca.setEventTrigger(new EventTrigger(2));


        ChargingRuleDefinition crd = new ChargingRuleDefinition();
        crd.setChargingRuleName(new ChargingRuleName("PCC-100"));
        crd.setServiceIdentifier(new ServiceIdentifier(52));
        crd.setRatingGroup(new RatingGroup(2));
        crd.setPrecedence(new Precedence(1));
        crd.setOnline(new Online(1));
        crd.setOffline(new Offline(0));

        FlowInformation flowUp = new FlowInformation();
        flowUp.setFlowDirection(new FlowDirection(2));
        flowUp.setTosTrafficClass(new TosTrafficClass(HexFormat.of().parseHex("68fc")));
        flowUp.setFlowDescription(new FlowDescription("permit out 17 from 172.16.20.111/32 to 172.17.241.255 17104"));

        FlowInformation flowDown = new FlowInformation();
        flowDown.setFlowDirection(new FlowDirection(1));
        flowDown.setTosTrafficClass(new TosTrafficClass(HexFormat.of().parseHex("68fc")));
        flowDown.setFlowDescription(new FlowDescription("permit in 17 from 172.17.241.255 17104 to 172.16.20.111/32"));

        QoSInformation qoSInformation = new QoSInformation();
        qoSInformation.setMaxRequestedBandwidthDL(new MaxRequestedBandwidthDL(16000));
        qoSInformation.setMaxRequestedBandwidthUL(new MaxRequestedBandwidthUL(32000));
        qoSInformation.setQoSClassIdentifier(new QoSClassIdentifier(9));
        AllocationRetentionPriority arp = new AllocationRetentionPriority();
        arp.setPreEmptionVulnerability(new PreEmptionVulnerability(1));
        arp.setPreEmptionCapability(new PreEmptionCapability(1));
        arp.setPriorityLevel(new PriorityLevel(15));
        qoSInformation.setAllocationRetentionPriority(arp);

        crd.setQoSInformation(qoSInformation);
        crd.setFlowInformation(flowUp);
        crd.setFlowInformation(flowDown);

        ChargingRuleInstall cri = new ChargingRuleInstall();
        cri.setChargingRuleDefinition(crd);
        cca.setChargingRuleInstall(cri);

        return cca;
    }
}