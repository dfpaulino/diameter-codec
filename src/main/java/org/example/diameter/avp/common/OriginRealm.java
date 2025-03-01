package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

import java.nio.charset.StandardCharsets;

import static org.example.diameter.avp.AvpDecoders.OctectStringDecoder;

public class OriginRealm extends Avp<String> {
    public OriginRealm(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return OctectStringDecoder.decode(buffer,position,header);
    }
}
