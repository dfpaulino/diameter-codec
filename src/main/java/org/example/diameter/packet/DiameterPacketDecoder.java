package org.example.diameter.packet;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpBuilder;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.ByteToAvpMapper;
import org.example.diameter.utils.ReadAvpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DiameterPacketDecoder {
    private static final Logger logger = LoggerFactory.getLogger(DiameterPacketDecoder.class);
    private static final ByteToAvpMapper BYTE_TO_AVP_MAPPER = ByteToAvpMapper.getInstance();

    public static <T extends DiameterPacket<?>> T packetDecode(T packet) {
        int position = 20;
        while (position < packet.getHeader().getMessageLength()) {
            //decode AVP Header
            AvpHeader avpHeaderheader = ReadAvpHeader.readAvpHeaderFromBytes(packet.getBuffer(), position);
            // what AVP is this?
            // if supported....else skip and push position for next avp based on avpHeader lengh
            //read Data from AVP
            //create instance of avp eg new OriginHost(headers,buffer,position,)
            AvpBuilder avpBuilder = BYTE_TO_AVP_MAPPER.getAvpBuilderMapper().get(avpHeaderheader.getAvpCode());
            if (avpBuilder != null) {
                Avp<?> avp = avpBuilder.builder().createAvp(avpHeaderheader, packet.getBuffer(), position);
                String methodName = "set" + avp.getClass().getSimpleName();
                try {
                    Method method = packet.getClass().getDeclaredMethod(methodName, avp.getClass());
                    method.invoke(packet, avp);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    logger.warn("AVP [{}] seems does not belong to this group... ignoring", avp.getClass().getSimpleName());
                    logger.warn("Exception {}", e);
                    //throw new RuntimeException(e);
                }
            } else {
                logger.warn("Unknown AVP ID [{}]", avpHeaderheader.getAvpCode());
            }

            position += avpHeaderheader.getAvpLength() + avpHeaderheader.getPaddingSize();
        }
        return packet;
    }
}
