package org.example.diameter.packet.factory;

import org.example.diameter.packet.DiameterPacket;
import org.example.diameter.packet.DiameterPacketHeader;

public interface DiameterPacketFactory {
    DiameterPacket of(DiameterPacketHeader header, byte[] buffer);
}
