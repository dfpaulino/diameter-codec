package org.example.diameter.packet;


import org.example.diameter.packet.messages.CreditControlRequest;
import org.example.diameter.utils.ReadDiameterHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// /home/dpaulino/Downloads/Complete-Gx-Esy-CIPIP-Qos-Change.pcap

class CreditControlRequestTest {
    private final byte[] buffer = HexFormat.of().parseHex("01000288c000011001000016237350b12375ff9d000001074000004d707473642d352e6d6f64756c652d312e54504550545330312e74616977616e6d6f62696c652e636f6d3b313431313031383736313b303b3139322e3136382e3230322e3239000000000001024000000c010000160000010840000031707473642d352e6d6f64756c652d312e54504550545330312e74616977616e6d6f62696c652e636f6d000000000001284000001874616977616e6d6f62696c652e636f6d0000011b4000001d736170632e74616977616e6d6f62696c652e636f6d000000000001a04000000c000000010000019f4000000c00000000000001164000000c5416d235000001bb40000028000001c24000000c00000000000001bc4000001438383639383731373535363800000274c0000038000028af0000010a4000000c000028af0000027580000010000028af000000010000027680000010000028af0000000b00000400c0000010000028af00000000000000084000000cc0a8ca1d00000403c0000010000028af000000000000040880000010000028af000003e8000001ca4000002c000001cb4000000c00000000000001cc4000001833353236343830353738363935383031000003f8c000002c000028af00000204c0000010000028af00007d0000000203c0000010000028af00007d0000000405c0000010000028af0000000100000406c0000010000028af0000000100000006c0000010000028af3d1f2bf300000016c0000014000028af0064f6790001ea6c0000001e4000000f6c74652e70756200000003e8c0000010000028af00000000000001f5c0000012000028af0001c0a8c8c30000000003fec000001c000028af000001f7c0000010000028af73000340");

    @Test
    public void decode() {
        DiameterPacketHeader header = ReadDiameterHeader.readDiameterHeaderFromBytes(buffer);
        CreditControlRequest ccr = new CreditControlRequest(header, buffer);


        // assert headers
        assertThat(ccr.getHeader().getCommandCode()).isEqualTo(272);
        assertThat(ccr.getHeader().getCommandFlags()).isEqualTo((byte) 0xc0);
        assertThat(ccr.getHeader().getMessageLength()).isEqualTo(648);
        assertThat(ccr.getHeader().getApplicationId()).isEqualTo(16777238);

        assertThat(ccr.getSessionId().getData()).isEqualTo("ptsd-5.module-1.TPEPTS01.taiwanmobile.com;1411018761;0;192.168.202.29");
        assertThat(ccr.getOriginHost().getData()).isEqualTo("ptsd-5.module-1.TPEPTS01.taiwanmobile.com");
        assertThat(ccr.getOriginRealm().getData()).isEqualTo("taiwanmobile.com");
        assertThat(ccr.getDestinationRealm().getData()).isEqualTo("sapc.taiwanmobile.com");
        assertThat(ccr.getCcRequestType().getData()).isEqualTo(1);
        assertThat(ccr.getCcRequestNumber().getData()).isEqualTo(0);
        assertThat(ccr.getSubscriptionId().get(0).getData().getSubscriptionIdData().getData()).isEqualTo("886987175568");
        assertThat(ccr.getSubscriptionId().get(0).getData().getSubscriptionIdType().getData()).isEqualTo(0);
        assertThat(ccr.getSupportedFeatures().get(0).getData().getFeatureList().getData()).isEqualTo(HexFormat.of().parseHex("0000000b"));
        assertThat(ccr.getSupportedFeatures().get(0).getData().getFeatureListId().getData()).isEqualTo(1);
        assertThat(ccr.getNetworkRequestSupport().getData()).isEqualTo(0);

        byte[] ip = ccr.getFrameIpAddress().getData();
        String ipStr = (ip[0] & 0xff) + "." + (ip[1] & 0xff) + "." + (ip[2] & 0xff) + "." + (ip[3] & 0xff);
        assertThat(ipStr).isEqualTo("192.168.202.29");

        assertThat(ccr.getIpCanType().getData()).isEqualTo(0);
        assertThat(ccr.getRatType().getData()).isEqualTo(1000);
        assertThat(ccr.getUserEquipmentInfo().getData().getUserEquipmentInfoType().getData()).isEqualTo(0);
        assertThat(ccr.getUserEquipmentInfo().getData().getUserEquipmentInfoValue().getData()).isEqualTo("3526480578695801");

        assertThat(ccr.getQoSInformation().getData().getMaxRequestedBandwidthDL().getData()).isEqualTo(32000);
        assertThat(ccr.getQoSInformation().getData().getMaxRequestedBandwidthUL().getData()).isEqualTo(32000);
        assertThat(ccr.getQoSNegotiation().getData()).isEqualTo(1);
        assertThat(ccr.getQoSUpgrade().getData()).isEqualTo(1);

        ip = ccr.getGppSgsnAddress().getData();
        ipStr = (ip[0] & 0xff) + "." + (ip[1] & 0xff) + "." + (ip[2] & 0xff) + "." + (ip[3] & 0xff);
        assertThat(ipStr).isEqualTo("61.31.43.243");
        assertThat(ccr.getGppUserLocationInfo().getData()).isEqualTo(HexFormat.of().parseHex("0064f6790001ea6c"));
        assertThat(ccr.getCalledStationId().getData()).isEqualTo("lte.pub");
        assertThat(ccr.getBearerUsage().getData()).isEqualTo(0);

        ipStr = ccr.getAccessNetworkChargingAddress().getData().getIp();
        assertThat(ipStr).isEqualTo("192.168.200.195");

        assertThat(ccr.getAccessNetworkChargingIdentifierGx().get(0).getData().getAccessNetworkChargingIdentifierValue().getData())
                .isEqualTo(HexFormat.of().parseHex("73000340"));


    }

    @Test
    public void CreditControlRequest_Gx_Decode() {
        //diameter.Session-Id == "string;459;844;IMSI999991234567810"
        byte[] diamPacket = HexFormat.of().parseHex("01000304c000011001000016b60adc87f267af85000001074000002a737472696e673b3435393b3834343b494d53493939393939313233343536373831300000000001024000000c01000016000001a04000000c000000010000019f4000000c00000000000001bb4000002c000001c24000000c00000001000001bc4000001739393939393132333435363738313000000001bb40000028000001c24000000c00000000000001bc4000001231323334353637383130000000000403c0000010000028af000000050000040880000010000028af000003ec000000084000000cac11f1ff0000001e40000010696e7465726e65740000027480000038000028af0000010a4000000c000028af0000027580000010000028af000000010000027680000010000028af0000000300000400c0000010000028af00000000000003f0c0000010000028af00000000000003f1c0000010000028af00000001000003e8c0000010000028af000000000000000c8000000d000028af300000000000000680000010000028afac100a650000000780000010000028afac100a650000041a80000012000028af0001ac100a650000000001f5c0000012000028af0001ac100a650000000001ca0000002c000001cb0000000c00000000000001cc00000018040009040107050303070706000000000000001280000011000028af30303130310000000000001680000019000028af8200f110000100f11000000101000000000003f8c000002c000028af0000041080000010000028af05c81a400000041180000010000028af02cd29c00000041980000058000028af00000404c0000010000028af000000090000040a8000003c000028af0000041680000010000028af000000090000041780000010000028af000000010000041880000010000028af00000001000001084000000e737472696e670000000001284000000e737472696e670000000001164000000c609208840000011b400000116d61676d612e636f6d000000000001254000001d6d61676d612d66656467772e6d61676d612e636f6d000000");

        DiameterPacketHeader header = ReadDiameterHeader.readDiameterHeaderFromBytes(diamPacket);
        CreditControlRequest ccr = new CreditControlRequest(header,diamPacket);
        assertThat(ccr.getQoSInformation().getData()).isNotNull();

        assertThat(ccr.getHeader().getCommandCode()).isEqualTo(272);
        assertThat(ccr.getHeader().getCommandFlags()).isEqualTo((byte) 0xc0);
        assertThat(ccr.getHeader().getMessageLength()).isEqualTo(772);
        assertThat(ccr.getHeader().getApplicationId()).isEqualTo(16777238);

        assertThat(ccr.getSessionId().getData()).isEqualTo("string;459;844;IMSI999991234567810");
        assertThat(ccr.getOriginHost().getData()).isEqualTo("string");
        assertThat(ccr.getOriginRealm().getData()).isEqualTo("string");
        assertThat(ccr.getDestinationRealm().getData()).isEqualTo("magma.com");
        assertThat(ccr.getDestinationHost().getData()).isEqualTo("magma-fedgw.magma.com");
        assertThat(ccr.getCcRequestType().getData()).isEqualTo(1);
        assertThat(ccr.getCcRequestNumber().getData()).isEqualTo(0);
        assertThat(ccr.getSubscriptionId().get(1).getData().getSubscriptionIdData().getData()).isEqualTo("1234567810");
        assertThat(ccr.getSubscriptionId().get(1).getData().getSubscriptionIdType().getData()).isEqualTo(0);
        assertThat(ccr.getNetworkRequestSupport().getData()).isEqualTo(0);
        assertThat(ccr.getQoSInformation().getData().getApnAggregateMaxBitrateDL().getData()).isEqualTo(97000000);
        assertThat(ccr.getGppUserLocationInfo().getData()).isEqualTo(HexFormat.of().parseHex("8200f110000100f11000000101"));
        assertThat(ccr.getDefaultEpsBearerQoS().getData().getAllocationRetentionPriority().getData().getPriorityLevel().getData()).isEqualTo(9);

    }
}