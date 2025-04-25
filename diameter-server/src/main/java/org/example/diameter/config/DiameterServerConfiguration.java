package org.example.diameter.config;


import org.example.diameter.DiameterReqContext;
import org.example.diameter.handlers.DiameterPacketHandler;
import org.example.diameter.handlers.Gx.*;
import org.example.diameter.handlers.PacketHandlerApplicationIdRouter;
import org.example.diameter.handlers.common.BasePacketHandler;
import org.example.diameter.packet.factory.DiameterPacketFactory;
import org.example.diameter.packet.factory.DiameterPacketFactoryImpl;
import org.example.diameter.properties.DiameterServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Configuration
public class DiameterServerConfiguration {

    @Autowired
    private DiameterServerProperties properties;

    @Bean
    public DiameterPacketFactory diameterPacketFactory() {
        return new DiameterPacketFactoryImpl();
    }

    @Bean("packetHandlerApplicationIdRouter")
    public DiameterPacketHandler packetHandlerApplicationIdRouter(DiameterServerProperties properties) {

        CcrGxHandler ccrInitialHandler = new CcrInitialHandler(properties);
        CcrGxHandler ccrUpdateHandler = new CcrUpdateHandler(properties);
        CcrGxHandler ccrTerminateHandler = new CcrTerminateHandler(properties);
        DiameterPacketHandler GxPacketHandler = new GxPacketHandler(properties, ccrInitialHandler, ccrUpdateHandler, ccrTerminateHandler);
        return new PacketHandlerApplicationIdRouter(GxPacketHandler, new BasePacketHandler(properties));
    }

    @Bean
    public Sinks.Many<DiameterReqContext> sink() {
        Queue<DiameterReqContext> buffer = new ArrayBlockingQueue<>(properties.getInBufferSize());
        return Sinks.many().unicast().onBackpressureBuffer(buffer);
    }
}
