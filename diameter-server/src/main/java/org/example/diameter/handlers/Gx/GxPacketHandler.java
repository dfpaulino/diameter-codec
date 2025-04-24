package org.example.diameter.handlers.Gx;

import org.example.diameter.DiameterReqContext;
import org.example.diameter.ModifiableDiameterReqContext;
import org.example.diameter.avp.enums.CreditControlTypeEnum;
import org.example.diameter.handlers.DiameterPacketHandler;

import org.example.diameter.packet.DiameterPacket;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.example.diameter.properties.DiameterServerProperties;
import reactor.core.publisher.Mono;

public class GxPacketHandler implements DiameterPacketHandler {

    private final DiameterServerProperties properties;
    private final CcrGxHandler ccrInitialHandler;
    private final CcrGxHandler ccrUpdateHandler;
    private final CcrGxHandler ccrTerminateHandler;

    public GxPacketHandler(DiameterServerProperties properties,
                           CcrGxHandler ccrInitialHandler,
                           CcrGxHandler ccrUpdateHandler,
                           CcrGxHandler ccrTerminateHandler) {
        this.properties = properties;
        this.ccrInitialHandler = ccrInitialHandler;
        this.ccrUpdateHandler = ccrUpdateHandler;
        this.ccrTerminateHandler = ccrTerminateHandler;
    }

    public Mono<DiameterReqContext> handle(DiameterReqContext reqContext){
        CreditControlRequest ccr = (CreditControlRequest) reqContext.getRequest();
        CreditControlTypeEnum cctype = CreditControlTypeEnum.of(ccr.getCcRequestType().getData());
        return switch (cctype) {
            case INIT -> sessionInit(reqContext);
            case UPDATE -> sessionUpdate(reqContext);
            case TERMINATE -> sessionTerminate(reqContext);
        };
    }

    private DiameterReqContext fromAnswer(DiameterReqContext reqContext,DiameterPacket cca) {
        return ModifiableDiameterReqContext.create().from(reqContext).setResponse(cca);
    }
    private Mono<DiameterReqContext> sessionInit (DiameterReqContext reqContext) {
        CreditControlRequest ccr = (CreditControlRequest)reqContext.getRequest();
        return Mono.just(reqContext)
                .flatMap(ctx ->this.ccrInitialHandler.handle(ccr))
                .map(cca->this.fromAnswer(reqContext,cca));
    }

    private Mono<DiameterReqContext> sessionUpdate (DiameterReqContext reqContext) {
        CreditControlRequest ccr = (CreditControlRequest)reqContext.getRequest();
        return Mono.just(reqContext)
                .flatMap(ctx ->this.ccrUpdateHandler.handle(ccr))
                .map(cca->this.fromAnswer(reqContext,cca));
    }

    private Mono<DiameterReqContext> sessionTerminate (DiameterReqContext reqContext) {
        CreditControlRequest ccr = (CreditControlRequest)reqContext.getRequest();
        return Mono.just(reqContext)
                .flatMap(ctx ->this.ccrTerminateHandler.handle(ccr))
                .map(cca->this.fromAnswer(reqContext,cca));
    }
}
