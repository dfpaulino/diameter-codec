package org.example.diameter.packet.utils;

import org.example.diameter.packet.DiameterPacket;
import org.example.diameter.packet.DiameterPacketHeader;
import org.example.diameter.packet.ImmutableDiameterPacketHeader;

public class DiameterPacketGenerateBase {
    public static DiameterPacketHeader generateHeaderFrom(DiameterPacket from) {
        return ImmutableDiameterPacketHeader.builder()
                .version(from.getHeader().getVersion())
                .messageLength(0)
                //clear 1st bit
                .commandFlags((byte)(from.getHeader().getCommandFlags()&0x7F))
                .commandCode(from.getHeader().getCommandCode())
                .applicationId(from.getHeader().getApplicationId())
                .hopByHop(from.getHeader().getHopByHop())
                .end2End(from.getHeader().getEnd2End())
                .build();

    }
}
