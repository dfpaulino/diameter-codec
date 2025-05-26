package org.example.diameter.handlers.Gx.mappers;

import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.example.pcc.contexts.SessionInitContext;
import org.example.pcc.dto.SessionPolicy;

public class GxToPccHttpMapperImpl implements GxToPccHttpMapper {
    @Override
    public SessionInitContext fromCcrInit(CreditControlRequest ccr) {
        SessionInitContext.SessionInitContextBuilder builder = SessionInitContext.builder();
        //msisdn
        builder.gpsi(ccr.getSubscriptionId().stream()
                .filter(subscriptionId -> subscriptionId.getData().getSubscriptionIdType().getData()==0)
                .findFirst().get().getData().getSubscriptionIdData().getData());

        //imsi
        if(ccr.getSubscriptionId().stream()
                .filter(subscriptionId -> subscriptionId.getData().getSubscriptionIdType().getData()==1)
                .findFirst().isPresent()) {
            builder.supi(ccr.getSubscriptionId().stream()
                    .filter(subscriptionId -> subscriptionId.getData().getSubscriptionIdType().getData()==1)
                    .findFirst().get().getData().getSubscriptionIdData().getData());

        }

        long id =Long.valueOf(Math.abs(ccr.getSessionId().getData().hashCode()));
        builder.pduSessionId(id);
        builder.dnn(ccr.getCalledStationId().getData());
        builder.ratType(ccr.getRatType().getData());
        builder.pei(ccr.getUserEquipmentInfo().getData().getUserEquipmentInfoValue().getData());
        if(null!=ccr.getFrameIpAddress().getData()) {
            byte[] ipv4Bytes = ccr.getFrameIpAddress().getData();
            String ipv4 =(ipv4Bytes[0] & 0xff) + "." + (ipv4Bytes[1] & 0xff) + "." + (ipv4Bytes[2] & 0xff) + "." + (ipv4Bytes[3] & 0xff);
            builder.ipv4Address(ipv4);
        }


        return builder.build();
    }

    @Override
    public CreditControlAnswer toCca(SessionPolicy sessionPolicy) {
        return null;
    }
}
