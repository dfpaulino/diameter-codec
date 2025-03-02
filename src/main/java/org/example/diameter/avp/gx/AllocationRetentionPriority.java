package org.example.diameter.avp.gx;

import lombok.Getter;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

public class AllocationRetentionPriority extends Avp<AllocationRetentionPriority> {
    public static int avpCode = 1034;
    public static byte flags = (byte) 0x80;
    @Getter
    private PriorityLevel priorityLevel;

    private void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public AllocationRetentionPriority(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public AllocationRetentionPriority decode(byte[] buffer, int position, AvpHeader header) {
        return null;
    }
}
