package org.example.diameter.packet;

import org.example.diameter.avp.common.*;
import org.example.diameter.avp.enums.AuthApplicationIdEnum;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.avp.types.Address;
import org.example.diameter.packet.enums.DiameterApplicationId;
import org.example.diameter.packet.enums.DiameterCmdCode;
import org.example.diameter.packet.messages.CapabilitiesExchangeRequest;
import org.example.diameter.utils.ReadDiameterHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CapabilitiesExchangeRequestTest {
    private static byte[] buffer = HexFormat.of().parseHex("0100010480000101000000001dc0b0828e4e21f80000010840000018706372662e6c6f63616c646f6d61696e00000128400000136c6f63616c646f6d61696e00000001164000000c5e6948e4000001014000000e00017f00000500000000010a4000000c000000000000010d00000014667265656469616d657465720000010b0000000c0000283e0000012b4000000c000000000000010440000020000001024000000c010000140000010a4000000c000028af0000010440000020000001024000000c010000160000010a4000000c000028af000001024000000cffffffff000001094000000c0000159f000001094000000c000028af000001094000000c000032db");

    @Test
    void decode() {
        DiameterPacketHeader header = ReadDiameterHeader.readDiameterHeaderFromBytes(buffer);
        CapabilitiesExchangeRequest cer = new CapabilitiesExchangeRequest(header,buffer);

        assertThat(header.getCommandCode()).isEqualTo(DiameterCmdCode.CAPABILITIES_EXCHANGE.getValue());
        assertThat(cer.getHeader().getCommandFlags()&0x80).isEqualTo(0x80);

        assertThat(cer.getOriginHost().getData()).isEqualTo("pcrf.localdomain");
        assertThat(cer.getOriginRealm().getData()).isEqualTo("localdomain");
        assertThat(cer.getHostIpAddress().getData().getIp()).isEqualTo("127.0.0.5");
        assertThat(cer.getProductName().getData()).isEqualTo("freediameter");

        assertThat(cer.getVendorSpecificApplicationId().size()).isEqualTo(2);
        assertThat(cer.getVendorSpecificApplicationId().getFirst().getData().getVendorId().getData())
                .isEqualTo(VendorId.GPP.getValue());
        assertThat(cer.getVendorSpecificApplicationId().get(1).getData().getVendorId().getData())
                .isEqualTo(VendorId.GPP.getValue());
        assertThat(cer.getVendorSpecificApplicationId().get(1).getData().getAuthApplicationId().getData())
                .isEqualTo(DiameterApplicationId._3GPP_GX.getValue());
        assertThat(cer.getSupportedVendorId().size()).isEqualTo(3);

    }

    @Test
    void encode() {
        CapabilitiesExchangeRequest cer = new CapabilitiesExchangeRequest();
        DiameterPacketHeader header = ImmutableDiameterPacketHeader.builder()
                .version((byte)0x01)
                .commandCode(DiameterCmdCode.CAPABILITIES_EXCHANGE.getValue())
                .commandFlags((byte)0x80)
                .messageLength(0) // message len calculated by encode
                .applicationId(DiameterApplicationId._3GPP_GX.getValue())
                .end2End(12345)
                .hopByHop(67890)
                .build();
        cer.setHeader(header);
        cer.setOriginHost(new OriginHost("freegx.localdomain"));
        cer.setOriginRealm(new OriginRealm("localdomain"));
        cer.setHostIpAddress(new HostIpAddress(new Address((short)1,"127.0.0.1")));
        cer.setProductName(new ProductName("freediameter"));
        cer.setVendorId(new org.example.diameter.avp.common.VendorId(VendorId.GPP.getValue()));
        VendorSpecificApplicationId vendorSpecificApplicationId = new VendorSpecificApplicationId();
        vendorSpecificApplicationId.setAuthApplicationId(new AuthApplicationId(AuthApplicationIdEnum.TGPP_GX.getValue()));
        vendorSpecificApplicationId.setVendorId(new org.example.diameter.avp.common.VendorId(VendorId.GPP.getValue()));
        cer.setVendorSpecificApplicationId(vendorSpecificApplicationId);

        byte[] encoded = cer.encode();

        // we should be able to decode and get the same data
        DiameterPacketHeader decodedHeader = ReadDiameterHeader.readDiameterHeaderFromBytes(encoded);
        CapabilitiesExchangeRequest decodedCer = new CapabilitiesExchangeRequest(decodedHeader,encoded);

        //assert header after decode
        assertThat(decodedCer.getHeader().getVersion()).isEqualTo((byte)01);
        assertThat(decodedCer.getHeader().getCommandCode()).isEqualTo(DiameterCmdCode.CAPABILITIES_EXCHANGE.getValue());
        assertThat(decodedCer.getHeader().getCommandFlags()&0x80).isEqualTo(0x80);
        assertThat(decodedCer.getHeader().getMessageLength()).isEqualTo(encoded.length);
        assertThat(decodedCer.getHeader().getApplicationId()).isEqualTo(DiameterApplicationId._3GPP_GX.getValue());
        assertThat(decodedCer.getHeader().getEnd2End()).isEqualTo(12345);
        assertThat(decodedCer.getHeader().getHopByHop()).isEqualTo(67890);

        //decode payload
        assertThat(decodedCer.getOriginHost().getData()).isEqualTo("freegx.localdomain");
        assertThat(decodedCer.getOriginRealm().getData()).isEqualTo("localdomain");
        assertThat(decodedCer.getHostIpAddress().getData().getIp()).isEqualTo("127.0.0.1");
        assertThat(decodedCer.getProductName().getData()).isEqualTo("freediameter");
        assertThat(decodedCer.getVendorSpecificApplicationId().size()).isEqualTo(1);
        assertThat(decodedCer.getVendorSpecificApplicationId().getFirst().getData().getVendorId().getData())
                .isEqualTo(VendorId.GPP.getValue());

    }
}