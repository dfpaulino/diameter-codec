package org.example.diameter.handlers.Gx;

import org.example.diameter.DiameterReqContext;
import org.example.diameter.handlers.DiameterPacketHandler;

import org.example.diameter.properties.DiameterServerProperties;
import reactor.core.publisher.Mono;

public class GxPacketHandler implements DiameterPacketHandler {

    private final DiameterServerProperties properties;

    public GxPacketHandler(DiameterServerProperties properties) {
        this.properties = properties;
    }

    public Mono<DiameterReqContext> handle(DiameterReqContext reqContext){
        // cast to CCR
        // handler CCR-I
        // handler CCR-U
        // handler CCR-T
        return Mono.just(reqContext);
    }
}
