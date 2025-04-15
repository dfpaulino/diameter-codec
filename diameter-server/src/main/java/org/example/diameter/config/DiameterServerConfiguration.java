package org.example.diameter.config;


import org.example.diameter.DiameterReqContext;
import org.example.diameter.packet.factory.DiameterPacketFactory;
import org.example.diameter.packet.factory.DiameterPacketFactoryImpl;
import org.example.diameter.service.DiameterPacketHandlerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Configuration
public class DiameterServerConfiguration {

    @Bean
    public DiameterPacketFactory diameterPacketFactory() {
        return new DiameterPacketFactoryImpl();
    }

    @Bean
    public Sinks.Many<DiameterReqContext> sink() {
        Queue<DiameterReqContext> q = new ArrayBlockingQueue<>(100);
        return Sinks.many().unicast().onBackpressureBuffer(q);
    }
}
