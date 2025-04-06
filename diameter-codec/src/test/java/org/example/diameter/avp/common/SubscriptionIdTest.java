package org.example.diameter.avp.common;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadAvpHeader;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SubscriptionIdTest {
    // this hex steam came from a tcpdump
    private static final byte[] buffer1 = HexFormat.of().parseHex("000001bb40000028000001c24000000c00000000000001bc40000014383836393837313735353638");
    private static final byte[] buffer2 = HexFormat.of().parseHex("000001bb4000002c000001c24000000c00000001000001bc4000001739393939393132333435363738313000");
    @Test
    public void decode_buffer1() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer1, 0);
        SubscriptionId subscriptionId = new SubscriptionId(header, buffer1, 0);
        // i return myself
        decodeAssert(subscriptionId,40,0,0,"886987175568");
    }

    @Test
    public void encode_from_buffer1() {
        // using the same data decoded from buffer1
        SubscriptionId subscriptionId = new SubscriptionId(new SubscriptionIdType(0),
                                                            new SubscriptionIdData("886987175568"));
        byte[] encoded = subscriptionId.encode();
        assertThat(encoded.length%4).isEqualTo(0);
        // this test might fail, as reflection does not guarantee order of fields!!
        assertThat(encoded).isEqualTo(buffer1);

        //we should be able to decode the payload...
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(encoded, 0);
        SubscriptionId subscriptionId2 = new SubscriptionId(header, encoded, 0);
        // i return myself
        decodeAssert(subscriptionId2,40,0,0,"886987175568");
    }

    private void decodeAssert(SubscriptionId subscriptionId,int expectedLength,int expectedPadding,int expectedType,String expectedData){
        assertThat(subscriptionId.getHeader().getPaddingSize()).isEqualTo(expectedPadding);
        assertThat(subscriptionId.getHeader().getAvpLength()).isEqualTo(expectedLength);
        assertThat(subscriptionId.getHeader().getAvpFlags()).isEqualTo((byte) 0x40);
        assertThat(subscriptionId.getHeader().isVendorSpecific()).isFalse();

        assertThat(subscriptionId.getData().getSubscriptionIdType().getData()).isEqualTo(expectedType);
        assertThat(subscriptionId.getData().getSubscriptionIdData().getData()).isEqualTo(expectedData);
    }

    @Test
    public void decode_buffer2() {
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(buffer2, 0);
        SubscriptionId subscriptionId = new SubscriptionId(header, buffer2, 0);
        // i return myself
        decodeAssert(subscriptionId,44,0,1,"999991234567810");
    }

    @Test
    public void encode_from_buffer2() {
        // using the same data decoded from buffer1
        SubscriptionId subscriptionId = new SubscriptionId(new SubscriptionIdType(1),
                new SubscriptionIdData("999991234567810"));
        byte[] encoded = subscriptionId.encode();
        assertThat(encoded.length%4).isEqualTo(0);

        assertThat(encoded).isEqualTo(buffer2);

        //we should be able to decode the payload...
        AvpHeader header = ReadAvpHeader.readAvpHeaderFromBytes(encoded, 0);
        SubscriptionId subscriptionId2 = new SubscriptionId(header, encoded, 0);
        // i return myself
        decodeAssert(subscriptionId2,44,0,1,"999991234567810");
    }
}