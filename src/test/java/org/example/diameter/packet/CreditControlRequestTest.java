package org.example.diameter.packet;


import org.example.diameter.utils.ReadDiameterHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CreditControlRequestTest {
    private byte[] buffer = HexFormat.of().parseHex("01000288c000011001000016237350b12375ff9d000001074000004d707473642d352e6d6f64756c652d312e54504550545330312e74616977616e6d6f62696c652e636f6d3b313431313031383736313b303b3139322e3136382e3230322e3239000000000001024000000c010000160000010840000031707473642d352e6d6f64756c652d312e54504550545330312e74616977616e6d6f62696c652e636f6d000000000001284000001874616977616e6d6f62696c652e636f6d0000011b4000001d736170632e74616977616e6d6f62696c652e636f6d000000000001a04000000c000000010000019f4000000c00000000000001164000000c5416d235000001bb40000028000001c24000000c00000000000001bc4000001438383639383731373535363800000274c0000038000028af0000010a4000000c000028af0000027580000010000028af000000010000027680000010000028af0000000b00000400c0000010000028af00000000000000084000000cc0a8ca1d00000403c0000010000028af000000000000040880000010000028af000003e8000001ca4000002c000001cb4000000c00000000000001cc4000001833353236343830353738363935383031000003f8c000002c000028af00000204c0000010000028af00007d0000000203c0000010000028af00007d0000000405c0000010000028af0000000100000406c0000010000028af0000000100000006c0000010000028af3d1f2bf300000016c0000014000028af0064f6790001ea6c0000001e4000000f6c74652e70756200000003e8c0000010000028af00000000000001f5c0000012000028af0001c0a8c8c30000000003fec000001c000028af000001f7c0000010000028af73000340");

    @Test
    public void decode() {
        DiameterPacketHeader header = ReadDiameterHeader.readDiameterHeaderFromBytes(buffer);
        DiameterPacket<CreditControlRequest> creditControlRequestDiameterPacket = new CreditControlRequest(header, buffer);
        CreditControlRequest ccr = creditControlRequestDiameterPacket.getData();

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
        assertThat(ccr.getSubscriptionId().get(0).getSubscriptionIdData().getData()).isEqualTo("886987175568");
        assertThat(ccr.getSubscriptionId().get(0).getSubscriptionIdType().getData()).isEqualTo(0);
        byte[] ip = ccr.getFrameIpAddress().getData();
        String ipStr = (ip[0] & 0xff) + "." + (ip[1] & 0xff) + "." + (ip[2] & 0xff) + "." + (ip[3] & 0xff);
        assertThat(ipStr).isEqualTo("192.168.202.29");
        assertThat(ccr.getSessionId().getData()).isEqualTo("ptsd-5.module-1.TPEPTS01.taiwanmobile.com;1411018761;0;192.168.202.29");
        assertThat(ccr.getAuthApplicationId().getData()).isEqualTo(16777238);


        System.out.println("hello");
    }

    private void asertThat() {
    }
}