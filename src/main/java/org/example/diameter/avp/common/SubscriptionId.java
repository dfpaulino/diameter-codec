package org.example.diameter.avp.common;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.Utils.ReadAvpHeader;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpIdToAvpMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SubscriptionId extends Avp<SubscriptionId> {

    @Getter
    private SubscriptionIdType subscriptionIdType;
    @Getter
    private SubscriptionIdData subscriptionIdData;

    private void setSubscriptionIdType(SubscriptionIdType subscriptionIdType) {
        this.subscriptionIdType = subscriptionIdType;
    }

    private void setSubscriptionIdData(SubscriptionIdData subscriptionIdData) {
        this.subscriptionIdData = subscriptionIdData;
    }

    public SubscriptionId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public SubscriptionId decode(byte[] buffer, int position, AvpHeader header) {
        return (SubscriptionId) AvpDecoders.GroupedAvpDecoder.decode(this,buffer,position,header);
    }

}
