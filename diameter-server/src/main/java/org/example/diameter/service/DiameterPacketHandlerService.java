package org.example.diameter.service;

import jakarta.annotation.PostConstruct;
import org.example.diameter.DiameterReqContext;
import org.example.diameter.DiameterSocketWriter;
import org.example.diameter.ModifiableDiameterReqContext;
import org.example.diameter.handlers.DiameterPacketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;


@Service
public class DiameterPacketHandlerService {
    private static final Logger logger = LoggerFactory.getLogger(DiameterPacketHandlerService.class);

    private  final Sinks.Many<DiameterReqContext> diameterFluxSink;
    private final DiameterPacketHandler packetHandler;
    private final DiameterSocketWriter socketWriter;
    private final Sinks.EmitFailureHandler emitFailureHandler = (signalType, emitResult) -> emitResult
            .equals(Sinks.EmitResult.FAIL_NON_SERIALIZED) ? true : false;


    public DiameterPacketHandlerService(Sinks.Many<DiameterReqContext> packetFluxSink,
                                        @Qualifier("packetHandlerApplicationIdRouter") DiameterPacketHandler packetHandler, DiameterSocketWriter socketWriter) {
        this.diameterFluxSink = packetFluxSink;
        this.packetHandler = packetHandler;
        this.socketWriter = socketWriter;
    }

    @PostConstruct
    private void initOn() {
        this.diameterFluxSink.asFlux().publishOn(Schedulers.parallel())

                // based on ApplicationId route to properHandler
                //.map(DiameterReqContext::getResponse)
                .doOnNext(c -> logger.debug("Processing incoming diameter packet cmd {}",
                        c.getRequest().getHeader().getCommandCode()))
                .flatMap(packetHandler::handle)
                //write the response packet back to the client socket
                //.flatMap(this::validateBeforeWriteAndUpdateStats)
                .onErrorResume(this::onError)
                .subscribe(socketWriter::write);
    }

    public void handlePacket(DiameterReqContext reqContext) {
        // submit the packet to sink...if fails...then discarded?
        Sinks.EmitResult result = this.diameterFluxSink.tryEmitNext(reqContext);
        if(Sinks.EmitResult.OK !=result){
            logger.error("Error submiting [{}]. Discarding packet.",result.name());
        };
    }

    private Mono<DiameterReqContext> validateBeforeWriteAndUpdateStats(ModifiableDiameterReqContext context) {
        if (context.responseIsSet()) {
            return Mono.just(context);
        } else {
            return Mono.empty();
        }
    }
    //TODO must generate default CCA with Error
    private Mono<DiameterReqContext> onError(Throwable t) {
        logger.error("Error Resuming ...",t);
        return Mono.empty();
    }
}
