package org.example.diameter.avp.gx;


import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.avp.types.Time;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RuleActivationTimeTest {
    private static byte[] BUFFER = HexFormat.of().parseHex("000000374000000ce43d7f14");
    @Test
    public void encode(){

        RuleActivationTime ruleActivationTime = new RuleActivationTime(Time.ofNSecondsFromNow(3600));
        System.out.println(new Date());
        String hex = HexFormat.of().formatHex(ruleActivationTime.encode());
        System.out.println(hex);
        System.out.println(ruleActivationTime.getData().getTime());

    }

    @Test
    public void decode(){
        /*
        00000413c0000010000028afeb8adef8
            2025-03-23T19:26:48
         */

        byte[] buffer = HexFormat.of().parseHex("00000413c0000010000028afeb8adef8");
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);
        RuleActivationTime ruleActivationTime = new RuleActivationTime(header,buffer,0);
        assertThat(header.getAvpCode()).isEqualTo(RuleActivationTime.avpCode);
        assertThat(header.getVendorId().get()).isEqualTo(VendorId.GPP.getValue());
        System.out.println(ruleActivationTime.getData().getTime());
        //assertThat(ruleActivationTime.getData().getTime()).isEqualTo()

    }

}