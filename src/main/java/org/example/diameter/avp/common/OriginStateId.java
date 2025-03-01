package org.example.diameter.avp.common;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

import static org.example.diameter.avp.AvpDecoders.IntegerDecoder;

public class OriginStateId extends Avp<Integer> {

    public OriginStateId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer,position );
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return IntegerDecoder.decode(buffer,position,header);
    }
}
