package org.example.diameter.handlers.Gx;

import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.example.diameter.properties.DiameterServerProperties;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CcrTerminateHandler implements CcrGxHandler{
    private final DiameterServerProperties properties;

    public CcrTerminateHandler(DiameterServerProperties properties) {
        this.properties = properties;
    }

    @Override
    public Mono<CreditControlAnswer> handle(CreditControlRequest ccr) {
        return Mono.empty();
    }
}
