package org.example.diameter.packet.factory;


import org.example.diameter.packet.*;
import org.example.diameter.packet.enums.DiameterCmdCode;
import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiameterPacketDecodeGxFactory implements DiameterPacketFactory{

    private static final Logger logger = LoggerFactory.getLogger(DiameterPacketDecodeGxFactory.class);

    public DiameterPacket of(DiameterPacketHeader header, byte[] buffer) {
        DiameterPacket diameterPacket = null;

        if((header.getCommandFlags()&0x80) == 0x80) {
            // TODO support DWD
            switch (DiameterCmdCode.of(header.getCommandCode())){
                case CREDIT_CONTROL:diameterPacket=new CreditControlRequest(header,buffer);break;
                default:logger.warn("unsupported diameter message {}. to be discarded",header.getCommandCode());
            }
        } else {
            // TODO support RAA
            switch (DiameterCmdCode.of(header.getCommandCode())){
                case CREDIT_CONTROL:diameterPacket=new CreditControlAnswer(header,buffer);break;
            }
        }
        return diameterPacket;
    }
}
