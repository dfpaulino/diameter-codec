package org.example.diameter.utils;

import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.ImmutableAvpHeader;

import java.util.Optional;

public class ReadAvpHeader {

    public static AvpHeader readAvpHeaderFromBytes(byte[] buffer, int initPosition) {

        int position = initPosition;

        int avpCode = ReadBytesUtils.readNBytesAsInt(buffer, position, 4);
        position += 4;
        byte avpFlags = ReadBytesUtils.readByteAsByte(buffer, position);
        position += 1;
        int avpLen = ReadBytesUtils.readNBytesAsInt(buffer, position, 3);
        position += 3;

        int vendorId = 0;
        if ((avpFlags & 0x80) == 0x80) {
            // is vendor specific
            vendorId = ReadBytesUtils.readNBytesAsInt(buffer, position, 4);
            position = vendorId;
        }

        AvpHeader avpHeader = ImmutableAvpHeader.builder()
                .avpCode(avpCode)
                .avpFlags(avpFlags)
                .avpLength(avpLen)
                .vendorId(vendorId == 0 ? Optional.empty() : Optional.of(vendorId))
                .build();
        return avpHeader;

    }
}
