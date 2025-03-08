package org.example.diameter.avp.rx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.common.GppUserLocationInfo;
import org.example.diameter.avp.types.Address;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccessNetworkChargingAddressTest {
    private byte[] buffer = HexFormat.of().parseHex("000001f5c0000012000028af0001c0a8c8c30000");
    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer,0);
        Avp<Address> accessNetworkChargingAddress = new AccessNetworkChargingAddress(header,buffer,0);
        Address address = accessNetworkChargingAddress.getData();

        assertThat(header.getAvpCode()).isEqualTo(AccessNetworkChargingAddress.avpCode);
        assertThat(header.getAvpFlags()).isEqualTo(AccessNetworkChargingAddress.flags);
        assertThat(header.getAvpLength()).isEqualTo(18);
        assertThat(header.getPaddingSize()).isEqualTo(2);

        assertThat(address.getFamily()).isEqualTo((short)1);
        assertThat(address.getIp()).isEqualTo("192.168.200.195");
    }

    // TODO test with IPV6
}