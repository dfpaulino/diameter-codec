package org.example.diameter.avp.common;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SubscriptionIdTest {
    private static final byte[] buffer = HexFormat.of().parseHex("000001bb40000028000001c24000000c00000000000001bc40000014383836393837313735353638");

    @Test
    public void decode() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer, 0);
        SubscriptionId subscriptionId = new SubscriptionId(header, buffer, 0);
        // i return myself
        SubscriptionId subscriptionId1 = subscriptionId.getData();
        assertThat(subscriptionId).isEqualTo(subscriptionId1);

        assertThat(subscriptionId.getHeader().getPaddingSize()).isEqualTo(0);
        assertThat(subscriptionId.getHeader().getAvpLength()).isEqualTo(40);
        assertThat(subscriptionId.getHeader().getAvpFlags()).isEqualTo((byte) 0x40);
        assertThat(subscriptionId.getHeader().isVendorSpecific()).isFalse();

        assertThat(subscriptionId.getSubscriptionIdType().getData()).isEqualTo(0);
        assertThat(subscriptionId.getSubscriptionIdData().getData()).isEqualTo("886987175568");
    }
}