package org.example.diameter.avp.gx;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;

import static org.example.diameter.avp.AvpDecoders.Integer32Decoder;

public class QoSNegotiation extends Avp<Integer> {
    public static int avpCode = 1029;
    public static byte flags = (byte) 0xc0;

    public QoSNegotiation(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public Integer decode(byte[] buffer, int position, AvpHeader header) {
        return Integer32Decoder.decode(buffer, position, header);
    }
}
