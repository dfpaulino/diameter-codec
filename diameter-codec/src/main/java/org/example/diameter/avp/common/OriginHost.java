package org.example.diameter.avp.common;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpRegister;
import org.example.diameter.utils.EncodeAvp;
import org.example.diameter.utils.EncodeUtils;

import static org.example.diameter.avp.AvpTypeDecoders.OctectStringUTF8Decoder;

/*
 Each AVP of type OctetString MUST be padded to align on a 32-bit
   boundary, while other AVP types align naturally.  A number of zero-
   valued bytes are added to the end of the AVP Data field till a word
   boundary is reached.  The length of the padding is not reflected in
   the AVP Length field.
 */
@AvpRegister(avpCode =264,avpBuilderMethod = "byteToAvp")
public class OriginHost extends Avp<String> {
    public static int avpCode = 264;
    public static byte flags = 0x40;

    public OriginHost(AvpHeader header, byte[] rawData, int position) {
        super(header, rawData, position);
    }

    public OriginHost(String data) {
        super(data);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((OriginHost::new));
    }

    @Override
    public String decode(byte[] buffer, int position, AvpHeader header) {
        return OctectStringUTF8Decoder.decode(buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encode(avpCode,flags,this.getData().length(),0,
                EncodeUtils.OctectStringUTF8ToBytes(this.getData()));
    }
}
