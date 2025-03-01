package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.javatuples.Pair;

public class HostIpAddress extends Avp<Pair<Short,String>> {
    public HostIpAddress(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Pair<Short, String> decode(byte[] buffer, int position, AvpHeader header) {
        return null;
    }
}
