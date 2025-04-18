package org.example.diameter.handlers.error;

import org.example.diameter.DiameterReqContext;
import org.example.diameter.handlers.DiameterPacketHandler;
import reactor.core.publisher.Mono;

/*

 */
public class ErrorPacketHandler implements DiameterPacketHandler {
    @Override
    public Mono<DiameterReqContext> handle(DiameterReqContext reqContext) {
        return null;
    }
}
