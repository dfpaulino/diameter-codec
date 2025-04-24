package org.example.diameter.handlers.Gx;

import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CcrUpdateHandler implements CcrGxHandler{
    @Override
    public Mono<CreditControlAnswer> handle(CreditControlRequest ccr) {
        return Mono.empty();
    }
}
