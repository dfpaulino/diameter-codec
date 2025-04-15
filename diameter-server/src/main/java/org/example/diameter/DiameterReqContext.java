package org.example.diameter;

import org.example.diameter.packet.DiameterPacket;
import org.immutables.value.Value;

import java.nio.channels.SocketChannel;

@Value.Modifiable
public interface DiameterReqContext {
    DiameterPacket getRequest();
    DiameterPacket getResponse();
    SocketChannel getSocketChannel();
    long getReceivedTime();
    long getRespTime();
}
