package org.example.diameter.handlers.Gx;

import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import reactor.core.publisher.Mono;

public interface CcrGxHandler {
    public Mono<CreditControlAnswer> handle(CreditControlRequest ccr);
}
