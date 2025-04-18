package org.example.diameter.handlers;

import org.example.diameter.DiameterReqContext;

import reactor.core.publisher.Mono;

public interface DiameterPacketHandler {
    Mono<DiameterReqContext> handle(DiameterReqContext reqContext);
}
