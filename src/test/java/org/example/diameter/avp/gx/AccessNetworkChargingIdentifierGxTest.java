package org.example.diameter.avp.gx;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccessNetworkChargingIdentifierGxTest {
    private static byte[] buffer = HexFormat.of().parseHex("000003fec000001c000028af000001f7c0000010000028af73000340");

    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);
        AccessNetworkChargingIdentifierGx accessNetworkChargingIdentifierGx =
                new AccessNetworkChargingIdentifierGx(header,buffer,0);

        assertThat(accessNetworkChargingIdentifierGx.getHeader().getAvpCode()).isEqualTo(1022);
        assertThat(accessNetworkChargingIdentifierGx.getHeader().getAvpFlags()).isEqualTo((byte)0xc0);
        assertThat(accessNetworkChargingIdentifierGx.getHeader().getAvpCode()).isEqualTo(1022);
        assertThat(accessNetworkChargingIdentifierGx.getHeader().getAvpLength()).isEqualTo(28);
        assertThat(accessNetworkChargingIdentifierGx.getHeader().getVendorId().get()).isEqualTo(VendorId.GPP.getValue());

        assertThat(accessNetworkChargingIdentifierGx.getData().getAccessNetworkChargingIdentifierValue().getData()).isEqualTo(HexFormat.of().parseHex("73000340"));

    }

    @Test
    void encode() {
        AccessNetworkChargingIdentifierGx accessNetworkChargingIdentifierGx =
                new AccessNetworkChargingIdentifierGx(new AccessNetworkChargingIdentifierValue(HexFormat.of().parseHex("73000340")));
        byte[] bytes = accessNetworkChargingIdentifierGx.encode();
        assertThat(bytes).isEqualTo(buffer);

        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(bytes,0);
        AccessNetworkChargingIdentifierGx accessNetworkChargingIdentifierGx1 =
                new AccessNetworkChargingIdentifierGx(header,bytes,0);
        assertThat(accessNetworkChargingIdentifierGx1.getData().getAccessNetworkChargingIdentifierValue().getData()).isEqualTo(HexFormat.of().parseHex("73000340"));

    }
}