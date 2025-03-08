package org.example.diameter.avp.common;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserEquipmentInfoTest {
    private byte[] buffer = HexFormat.of().parseHex("000001ca4000002c000001cb4000000c00000000000001cc4000001833353236343830353738363935383031");

    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer, 0);
        UserEquipmentInfo userEquipmentInfo = new UserEquipmentInfo(header, buffer, 0);
        assertThat(header.getAvpCode()).isEqualTo(UserEquipmentInfo.avpCode);
        assertThat(header.getAvpFlags()).isEqualTo(UserEquipmentInfo.flags);
        assertThat(userEquipmentInfo.getData().getUserEquipmentInfoType().getData()).isEqualTo(0);
        assertThat(userEquipmentInfo.getData().getUserEquipmentInfoValue().getData()).isEqualTo("3526480578695801");

    }
}