package org.example.diameter.utils;

import org.example.diameter.packet.DiameterPacketHeader;
import org.example.diameter.packet.ImmutableDiameterPacketHeader;

public class ReadDiameterHeader {

    public static DiameterPacketHeader readDiameterHeaderFromBytes(byte[] buffer) {

        int position = 0;
        byte version = ReadBytesUtils.readByteAsByte(buffer, position);
        position += 1;
        int length = ReadBytesUtils.readNBytesAsInt(buffer, position, 3);
        position += 3;
        byte flags = ReadBytesUtils.readByteAsByte(buffer, position);
        position += 1;
        int commandCode = ReadBytesUtils.readNBytesAsInt(buffer, position, 3);
        position += 3;
        long applicationId = ReadBytesUtils.readNBytesAsLong(buffer, position, 4);
        position += 4;
        long hopByHopId = ReadBytesUtils.readNBytesAsLong(buffer, position, 4);
        position += 4;
        long endToEndId = ReadBytesUtils.readNBytesAsLong(buffer, position, 4);


        DiameterPacketHeader diameterPacketHeader = ImmutableDiameterPacketHeader.builder()
                .version(version)
                .messageLength(length)
                .commandFlags(flags)
                .commandCode(commandCode)
                .applicationId(applicationId)
                .hopByHop(hopByHopId)
                .end2End(endToEndId)
                .build();
        return diameterPacketHeader;

    }
}
