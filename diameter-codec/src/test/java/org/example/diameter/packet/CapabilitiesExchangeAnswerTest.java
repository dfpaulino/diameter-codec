package org.example.diameter.packet;

import org.example.diameter.avp.common.*;
import org.example.diameter.avp.enums.AuthApplicationIdEnum;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.avp.types.Address;
import org.example.diameter.packet.enums.DiameterApplicationId;
import org.example.diameter.packet.enums.DiameterCmdCode;
import org.example.diameter.packet.messages.CapabilitiesExchangeAnswer;
import org.example.diameter.utils.ReadBytesUtils;
import org.example.diameter.utils.ReadDiameterHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CapabilitiesExchangeAnswerTest {
    private byte[] buffer = HexFormat.of().parseHex("010000e400000101000000001dc0b0828e4e21f80000010c4000000c000007d100000108400000177067772e6c6f63616c646f6d61696e0000000128400000136c6f63616c646f6d61696e00000001164000000c5e6948e4000001014000000e00017f00000300000000010a4000000c000000000000010d00000014667265656469616d657465720000010b0000000c0000283e0000010440000020000001024000000c010000160000010a4000000c000028af000001024000000cffffffff000001094000000c0000159f000001094000000c000028af000001094000000c000032db");

    @Test
    void decode() {
        DiameterPacketHeader diameterPacketHeader = ReadDiameterHeader.readDiameterHeaderFromBytes(buffer);

        CapabilitiesExchangeAnswer cea = new CapabilitiesExchangeAnswer(diameterPacketHeader,buffer);

        assertThat(cea.getHeader().getCommandCode()).isEqualTo(DiameterCmdCode.CAPABILITIES_EXCHANGE.getValue());
        assertThat(cea.getHeader().getCommandFlags()&0x80).isEqualTo(0x00);
        assertThat(cea.getHeader().getMessageLength()).isEqualTo(buffer.length);
        assertThat(cea.getHeader().getApplicationId()).isEqualTo(0);
        assertThat(cea.getHeader().getEnd2End()).isEqualTo(ReadBytesUtils.readNBytesAsLong(HexFormat.of().parseHex("8e4e21f8"),0,4 ));
        assertThat(cea.getHeader().getHopByHop()).isEqualTo(0x1dc0b082);

        assertThat(cea.getResultCode().getData()).isEqualTo(2001);
        assertThat(cea.getOriginHost().getData()).isEqualTo("pgw.localdomain");
        assertThat(cea.getOriginRealm().getData()).isEqualTo("localdomain");
        assertThat(cea.getHostIpAddress().getData().getIp()).isEqualTo("127.0.0.3");
        assertThat(cea.getProductName().getData()).isEqualTo("freediameter");
        assertThat(cea.getVendorSpecificApplicationId().size()).isEqualTo(1);
        assertThat(cea.getVendorSpecificApplicationId().getFirst().getData().getVendorId().getData())
                .isEqualTo(VendorId.GPP.getValue());
        assertThat(cea.getSupportedVendorId().size()).isEqualTo(3);
    }

    @Test
    void encode() {
        DiameterPacketHeader header = ImmutableDiameterPacketHeader.builder()
                .version((byte)0x01)
                .commandCode(DiameterCmdCode.CAPABILITIES_EXCHANGE.getValue())
                .commandFlags((byte)0x00)
                .messageLength(0) // message len calculated by encode
                .applicationId(DiameterApplicationId._3GPP_GX.getValue())
                .end2End(12345)
                .hopByHop(67890)
                .build();
        CapabilitiesExchangeAnswer cea = new CapabilitiesExchangeAnswer();
        cea.setHeader(header);

        cea.setOriginHost(new OriginHost("freegx.localdomain"));
        cea.setOriginRealm(new OriginRealm("localdomain"));
        cea.setHostIpAddress(new HostIpAddress(new Address((short)1,"127.0.0.1")));
        cea.setProductName(new ProductName("freediameter"));
        cea.setResultCode(new ResultCode(2001));
        cea.setVendorId(new org.example.diameter.avp.common.VendorId(VendorId.GPP.getValue()));
        VendorSpecificApplicationId vendorSpecificApplicationId = new VendorSpecificApplicationId();
        vendorSpecificApplicationId.setAuthApplicationId(new AuthApplicationId(AuthApplicationIdEnum.TGPP.getValue()));
        vendorSpecificApplicationId.setVendorId(new org.example.diameter.avp.common.VendorId(VendorId.GPP.getValue()));
        cea.setVendorSpecificApplicationId(vendorSpecificApplicationId);

        byte[] encoded = cea.encode();

        // we should be able to decode and get the same data
        DiameterPacketHeader decodedHeader = ReadDiameterHeader.readDiameterHeaderFromBytes(encoded);
        CapabilitiesExchangeAnswer decodedCea = new CapabilitiesExchangeAnswer(decodedHeader,encoded);

        //assert header after decode
        assertThat(decodedCea.getHeader().getVersion()).isEqualTo((byte)01);
        assertThat(decodedCea.getHeader().getCommandCode()).isEqualTo(DiameterCmdCode.CAPABILITIES_EXCHANGE.getValue());
        assertThat(decodedCea.getHeader().getCommandFlags()&0x80).isEqualTo(0x00);
        assertThat(decodedCea.getHeader().getMessageLength()).isEqualTo(encoded.length);
        assertThat(decodedCea.getHeader().getApplicationId()).isEqualTo(DiameterApplicationId._3GPP_GX.getValue());
        assertThat(decodedCea.getHeader().getEnd2End()).isEqualTo(12345);
        assertThat(decodedCea.getHeader().getHopByHop()).isEqualTo(67890);

        //decode payload
        assertThat(decodedCea.getOriginHost().getData()).isEqualTo("freegx.localdomain");
        assertThat(decodedCea.getOriginRealm().getData()).isEqualTo("localdomain");
        assertThat(decodedCea.getHostIpAddress().getData().getIp()).isEqualTo("127.0.0.1");
        assertThat(decodedCea.getProductName().getData()).isEqualTo("freediameter");
        assertThat(decodedCea.getVendorSpecificApplicationId().size()).isEqualTo(1);
        assertThat(decodedCea.getVendorSpecificApplicationId().getFirst().getData().getVendorId().getData())
                .isEqualTo(VendorId.GPP.getValue());

    }
}