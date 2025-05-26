package org.example.diameter.handlers.Gx;

import org.example.diameter.avp.common.ResultCode;
import org.example.diameter.avp.common.SessionId;
import org.example.diameter.avp.enums.ResultCodeEnum;
import org.example.diameter.handlers.Gx.mappers.GxToPccHttpMapper;
import org.example.diameter.handlers.Gx.mappers.PccHttpMapperToGx;
import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.example.diameter.properties.DiameterServerProperties;
import org.example.pcc.client.PccRestClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CcrInitialHandler implements CcrGxHandler{

    private final DiameterServerProperties properties;
    private final PccRestClient pccRestClient;
    private final GxToPccHttpMapper gxToPccHttpMapper;

    public CcrInitialHandler(DiameterServerProperties properties, PccRestClient pccRestClient, GxToPccHttpMapper mapper) {
        this.properties = properties;
        this.pccRestClient = pccRestClient;
        this.gxToPccHttpMapper = mapper;

    }

    @Override
    public Mono<CreditControlAnswer> handle(CreditControlRequest ccr) {
        return Mono.just(ccr).map(gxToPccHttpMapper::fromCcrInit)
                .flatMap(pccRestClient::createSession)
                .map(gxToPccHttpMapper::toCca)
                .map(cca -> appendBaseInfoToCreditControlAnswer(ccr,cca));
    }


    private CreditControlAnswer appendBaseInfoToCreditControlAnswer(CreditControlRequest ccr,CreditControlAnswer cca) {
        // Build the CreditControlAnswer based on the CreditControlRequest

        cca.setSessionId(new SessionId(ccr.getSessionId().getData()));
        cca.setResultCode(new ResultCode(ResultCodeEnum.DIAMETER_SUCCESS.getValue())); // Example result code
        // Set other fields as needed
        return cca;
    }
}
