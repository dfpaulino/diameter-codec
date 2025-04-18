package org.example.diameter.handlers;

import org.example.diameter.exceptions.ApplicationIdNotSupported;
import org.example.diameter.DiameterReqContext;
import org.example.diameter.packet.enums.DiameterApplicationId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;

public class PacketHandlerApplicationIdRouter implements DiameterPacketHandler {
    private static final Logger logger = LoggerFactory.getLogger(PacketHandlerApplicationIdRouter.class);

    private final DiameterPacketHandler gxPacketHandler;
    private final DiameterPacketHandler commonPacketHandler;

    public PacketHandlerApplicationIdRouter(DiameterPacketHandler gxPacketHandler, DiameterPacketHandler commonPacketHandler) {
        this.gxPacketHandler = gxPacketHandler;
        this.commonPacketHandler = commonPacketHandler;
    }

    @Override
    public Mono<DiameterReqContext> handle(DiameterReqContext reqContext) {
        long applicationId = reqContext.getRequest().getHeader().getApplicationId();

        return switch (DiameterApplicationId.of(applicationId)) {
            case COMMON -> commonPacketHandler.handle(reqContext);
            case _3GPP_GX -> gxPacketHandler.handle(reqContext);
            default -> Mono.error(new ApplicationIdNotSupported("ApplicationId not supported"));
        };
    }
}
