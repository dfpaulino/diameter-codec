package org.example.diameter.avp.gx;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class QoSInformationTest {
    private final byte[] buffer = HexFormat.of().parseHex("000003f8c000002c000028af00000204c0000010000028af00007d0000000203c0000010000028af00007d00");

    @Test
    void decode_buffer() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer, 0);
        QoSInformation qoSInformation = new QoSInformation(header, buffer, 0);
        qoSInformation.getData();

        assertThat(qoSInformation.getMaxRequestedBandwidthDL().getData()).isEqualTo(32000);
        assertThat(qoSInformation.getMaxRequestedBandwidthUL().getData()).isEqualTo(32000);
    }

    @Test
    public void encode_buffer() {
        QoSInformation qoSInformation = new QoSInformation();
        qoSInformation.setMaxRequestedBandwidthDL(new MaxRequestedBandwidthDL(32000));
        qoSInformation.setMaxRequestedBandwidthUL(new MaxRequestedBandwidthUL(32000));

        byte[] encoded = qoSInformation.encode();
        assertThat(encoded).isEqualTo(buffer);
    }
}