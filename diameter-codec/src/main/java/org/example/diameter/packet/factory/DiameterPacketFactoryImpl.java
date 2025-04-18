package org.example.diameter.packet.factory;

import org.example.diameter.packet.DiameterPacket;
import org.example.diameter.packet.DiameterPacketHeader;
import org.example.diameter.packet.enums.DiameterApplicationId;
import org.example.diameter.packet.enums.DiameterCmdCode;
import org.example.diameter.packet.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DiameterPacketFactoryImpl implements DiameterPacketFactory {

    private static final Logger logger = LoggerFactory.getLogger(DiameterPacketFactoryImpl.class);

    // TODO use Map<Int, Factories> to map from ApplicationId
    private final Map<Long,DiameterPacketFactory> diameterPacketApplicationFactory = new HashMap<>();

    public DiameterPacketFactoryImpl() {
        diameterPacketApplicationFactory.put((long)DiameterApplicationId._3GPP_GX.getValue(),new DiameterPacketDecodeGxFactory());
    }

    @Override
    public DiameterPacket of(DiameterPacketHeader header, byte[] buffer) {
        DiameterPacket diameterPacket = null;
        //check if REQ or RSP
        if((header.getCommandFlags()&0x80) == 0x80) {
            // TODO support DWD
            //check if CMD Code
            switch (DiameterCmdCode.of(header.getCommandCode())){
                case CREDIT_CONTROL:diameterPacket=getCcrPacket(header,buffer);break;
                case CAPABILITIES_EXCHANGE:diameterPacket=new CapabilitiesExchangeRequest(header,buffer);break;
                case DWD:diameterPacket = new DeviceWatchDogReq(header,buffer);
                default:logger.warn("unsupported diameter message {}. to be discarded",header.getCommandCode());
            }
        } else {
            // TODO support RAA
            switch (DiameterCmdCode.of(header.getCommandCode())){
                case CREDIT_CONTROL:diameterPacket=getCcrPacket(header,buffer);break;
            }
        }
        return diameterPacket;
    }

    private DiameterPacket getCcrPacket(DiameterPacketHeader header, byte[] buffer) {
        if(diameterPacketApplicationFactory.containsKey(header.getApplicationId())) {
            return diameterPacketApplicationFactory.get(header.getApplicationId()).of(header,buffer);
        }else {
            logger.warn("unsupported ApplicationId {}. to be discarded",header.getApplicationId());
            return null;
        }

    }
}
