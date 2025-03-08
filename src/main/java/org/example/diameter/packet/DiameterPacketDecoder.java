package org.example.diameter.packet;

import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpIdToAvpMapper;
import org.example.diameter.utils.ReadAvpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DiameterPacketDecoder {
    private static final Logger logger = LoggerFactory.getLogger(DiameterPacketDecoder.class);

    public  static <T extends DiameterPacket<?>> T packetDecode(T packet) {
        int position = 20;
        while (position < packet.getHeader().getMessageLength()) {
            //decode AVP Header
            AvpHeader avpHeaderheader = ReadAvpHeader.readAvpHeaderFromBytes(packet.getBuffer(), position);
            // what AVP is this?
            // if supported....else skip and push position for next avp based on avpHeader lengh
            //read Data from AVP
            //create instance of avp eg new OriginHost(headers,buffer,position,)
            AvpIdToAvpMapper.AvpDefinition avpDefinition =AvpIdToAvpMapper.getAvpMapper().get(avpHeaderheader
                    .getAvpCode());
            if(avpDefinition!=null) {
                Avp<?> avp = avpDefinition.avpCreator.createInstance(avpHeaderheader, packet.getBuffer(), position);
                avp.getData();
                String methodName = "set" + avp.getClass().getSimpleName();
                try {
                    //Method method = this.getClass().getDeclaredMethod(methodName, avp.getClass());
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

            position+=avpHeaderheader.getAvpLength()+ avpHeaderheader.getPaddingSize();
        }
        return packet;
    }
}
