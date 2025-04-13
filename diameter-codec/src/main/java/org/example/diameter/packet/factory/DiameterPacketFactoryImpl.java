package org.example.diameter.packet.factory;

import org.example.diameter.packet.DiameterPacket;
import org.example.diameter.packet.DiameterPacketHeader;
import org.example.diameter.packet.enums.DiameterApplicationId;
import org.example.diameter.packet.enums.DiameterCmdCode;
import org.example.diameter.packet.messages.CapabilitiesExchangeAnswer;
import org.example.diameter.packet.messages.CapabilitiesExchangeRequest;
import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiameterPacketFactoryImpl implements DiameterPacketFactory {

    private static final Logger logger = LoggerFactory.getLogger(DiameterPacketFactoryImpl.class);

    private final DiameterPacketFactory diameterPacketDecodeGxFactory;

    public DiameterPacketFactoryImpl() {
        this.diameterPacketDecodeGxFactory = new DiameterPacketDecodeGxFactory();
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
                default:logger.warn("unsupported diameter message {}. to be discarded",header.getCommandCode());
            }
        } else {
            // TODO support RAA
            switch (DiameterCmdCode.of(header.getCommandCode())){
                case CREDIT_CONTROL:diameterPacket=new CreditControlAnswer(header,buffer);break;
                case CAPABILITIES_EXCHANGE:diameterPacket=new CapabilitiesExchangeAnswer(header,buffer);break;
            }
        }
        return diameterPacket;
    }

    //get the correct diameter packet based on the ApplicationId(GX)
    private DiameterPacket getCcrPacket(DiameterPacketHeader header, byte[] buffer) {
        if(header.getApplicationId() == DiameterApplicationId._3GPP_GX.getValue()){
            return diameterPacketDecodeGxFactory.of(header,buffer);
        } else {
            logger.warn("unsupported ApplicationId {}. to be discarded",header.getApplicationId());
            return null;
        }
    }
}
