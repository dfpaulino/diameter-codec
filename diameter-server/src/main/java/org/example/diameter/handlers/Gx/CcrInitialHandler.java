package org.example.diameter.handlers.Gx;

import org.example.diameter.avp.common.ResultCode;
import org.example.diameter.avp.common.SessionId;
import org.example.diameter.avp.enums.ResultCodeEnum;
import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.example.diameter.properties.DiameterServerProperties;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CcrInitialHandler implements CcrGxHandler{

    private final DiameterServerProperties properties;

    public CcrInitialHandler(DiameterServerProperties properties) {
        this.properties = properties;
    }

    @Override
    public Mono<CreditControlAnswer> handle(CreditControlRequest ccr) {
        // ccr -> webclient ->flatmap(mapHttpToGx)
        return Mono.empty();
    }

    private CreditControlAnswer buildCreditControlAnswer(CreditControlRequest ccr) {
        // Build the CreditControlAnswer based on the CreditControlRequest
        CreditControlAnswer cca = new CreditControlAnswer();
        cca.setSessionId(new SessionId(ccr.getSessionId().getData()));
        cca.setResultCode(new ResultCode(ResultCodeEnum.DIAMETER_SUCCESS.getValue())); // Example result code
        // Set other fields as needed
        return cca;
    }
}
