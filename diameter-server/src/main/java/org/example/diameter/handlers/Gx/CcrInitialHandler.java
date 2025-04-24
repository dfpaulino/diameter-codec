package org.example.diameter.handlers.Gx;

import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CcrInitialHandler implements CcrGxHandler{
    @Override
    public Mono<CreditControlAnswer> handle(CreditControlRequest ccr) {
        // ccr -> webclient ->map Mono<cca>
        return Mono.empty();
    }
}
