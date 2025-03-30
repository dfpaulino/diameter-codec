package org.example.diameter.avp.gx;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FlowInformationTest {

    private static byte[] buffer = HexFormat.of().parseHex("00000422c0000074000028af000001fbc0000046000028af7065726d697420696e2031372066726f6d203137322e31372e3234312e32353520313731303520746f203137322e31362e32302e3131312f33320000000003f6c000000e000028af68fc000000000438c0000010000028af00000002");

    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);
        FlowInformation flowInformation = new FlowInformation(header,buffer,0);

        assertThat(flowInformation.getHeader().getAvpCode()).isEqualTo(1058);
        assertThat(flowInformation.getHeader().getAvpLength()).isEqualTo(116);
        assertThat(flowInformation.getData().getFlowDirection().getData()).isEqualTo(2);
        assertThat(flowInformation.getData().getTosTrafficClass().getData()).isEqualTo(HexFormat.of().parseHex("68fc"));
        assertThat(flowInformation.getData().getFlowDescription().getData()).isEqualTo("permit in 17 from 172.17.241.255 17105 to 172.16.20.111/32");
        assertThat(flowInformation.getData().getFlowDescription().getHeader().getPaddingSize()).isEqualTo(2);


    }

    @Test
    void encode() {
    }
}