package org.example.diameter.avp.gx;

import org.example.diameter.avp.AvpHeader;
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

    }

}