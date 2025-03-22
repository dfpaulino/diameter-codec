package org.example.diameter.avp.common;


import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.enums.AuthApplicationIdEnum;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AuthApplicationIdTest {
    private static byte[] BUFFER = HexFormat.of().parseHex("000001024000000c01000016");

    @Test
    public void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(BUFFER,0);
        AuthApplicationId authApplicationId = new AuthApplicationId(header,BUFFER,0);
        assertThat(authApplicationId.getHeader().getAvpCode()).isEqualTo(258);
        assertThat(authApplicationId.getHeader().getAvpLength()).isEqualTo(12);
        assertThat(authApplicationId.getData()).isEqualTo(AuthApplicationIdEnum.TGPP.getValue());
    }

    @Test
    public void encode(){
        //let do the inverse
        AuthApplicationId authApplicationId = new AuthApplicationId(AuthApplicationIdEnum.TGPP.getValue());
        byte[] buffer = authApplicationId.encode();
        assertThat(buffer).isEqualTo(BUFFER);

    }

}