package org.example.diameter.service;

import jakarta.annotation.PostConstruct;
import org.example.diameter.DiameterReqContext;
import org.example.diameter.packet.DiameterPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class DiameterPacketHandlerService {
    public static Logger logger = LoggerFactory.getLogger(DiameterPacketHandlerService.class);

    private  final Sinks.Many<DiameterReqContext> diameterFluxSink;
    private final Sinks.EmitFailureHandler emitFailureHandler = (signalType, emitResult) -> emitResult
            .equals(Sinks.EmitResult.FAIL_NON_SERIALIZED) ? true : false;


    public DiameterPacketHandlerService(Sinks.Many<DiameterReqContext> packetFluxSink) {
        this.diameterFluxSink = packetFluxSink;
    }

    @PostConstruct
    private void initOn() {
        this.diameterFluxSink.asFlux().publishOn(Schedulers.parallel())
                .subscribe(p->logger.info("processing packet {}",p.getRequest().getHeader().getCommandCode()));
    }

    public void handlePacket(DiameterReqContext reqContext) {
        Sinks.EmitResult result = this.diameterFluxSink.tryEmitNext(reqContext);
        if(Sinks.EmitResult.OK !=result){
            logger.error("Error submiting [{}]. Discarding packet.",result.name());
        };
    }
}
