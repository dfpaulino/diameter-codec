package org.example.diameter.avp.rx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.types.Address;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AccessNetworkChargingAddressTest {
    private final byte[] buffer = HexFormat.of().parseHex("000001f5c0000012000028af0001c0a8c8c30000");

    @Test
    void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer, 0);
        Avp<Address> accessNetworkChargingAddress = new AccessNetworkChargingAddress(header, buffer, 0);
        Address address = accessNetworkChargingAddress.getData();

        assertThat(header.getAvpCode()).isEqualTo(AccessNetworkChargingAddress.avpCode);
        assertThat(header.getAvpFlags()).isEqualTo(AccessNetworkChargingAddress.flags);
        assertThat(header.getAvpLength()).isEqualTo(18);
        assertThat(header.getPaddingSize()).isEqualTo(2);

        assertThat(address.getFamily()).isEqualTo((short) 1);
        assertThat(address.getIp()).isEqualTo("192.168.200.195");
    }

    @Test
    public void encode(){
        Address address = new Address((short)1,"192.168.200.195");
        Avp<Address> accessNetworkChargingAddress = new AccessNetworkChargingAddress(address);
        byte[] encoded = accessNetworkChargingAddress.encode();
        assertThat(encoded).isEqualTo(buffer);
    }



    // TODO test with IPV6
    @Test
    public void encodeIpv6(){
        Address address = new Address((short)2,"2001:db8:3333:4444:5555:6666:7777:8888");
        Avp<Address> accessNetworkChargingAddress = new AccessNetworkChargingAddress(address);
        byte[] encoded = accessNetworkChargingAddress.encode();
        assertThat(encoded.length%4).isEqualTo(0);
        System.out.println(HexFormat.of().formatHex(encoded));
    }

    @Test
    void decodeIpV6() {
        byte[] buff = HexFormat.of().parseHex("000001f5c000001e000028af000220010db83333444455556666777788880000");
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buff, 0);
        Avp<Address> accessNetworkChargingAddress = new AccessNetworkChargingAddress(header, buff, 0);
        Address address = accessNetworkChargingAddress.getData();

        assertThat(header.getAvpCode()).isEqualTo(AccessNetworkChargingAddress.avpCode);
        assertThat(header.getAvpFlags()).isEqualTo(AccessNetworkChargingAddress.flags);
        assertThat(header.getAvpLength()).isEqualTo(30);
        assertThat(header.getPaddingSize()).isEqualTo(2);

        assertThat(address.getFamily()).isEqualTo((short) 2);
        assertThat(address.getIp()).isEqualTo("2001:0db8:3333:4444:5555:6666:7777:8888");
    }



}