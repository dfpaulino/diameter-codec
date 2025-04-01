package org.example.diameter.packet;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.common.*;
import org.example.diameter.avp.gx.*;
import org.example.diameter.avp.rx.AccessNetworkChargingAddress;

import java.util.ArrayList;
import java.util.List;

/*
Message Format

 <CC-Request> ::= < Diameter Header: 272, REQ, PXY >

 */

public class CreditControlAnswer extends DiameterPacket<CreditControlAnswer> {

    // AVP definitions
    @Setter
    @Getter
    private SessionId sessionId;
    @Setter
    @Getter
    private AuthApplicationId authApplicationId;
    @Setter
    @Getter
    private OriginHost originHost;
    @Setter
    @Getter
    private OriginRealm originRealm;
    @Setter
    @Getter
    private ResultCode resultCode;
    @Setter
    @Getter
    private CcRequestType ccRequestType;
    @Setter
    @Getter
    private CcRequestNumber ccRequestNumber;
    @Setter
    @Getter
    private OriginStateId originStateId;
    @Setter@Getter
    private BearerControlMode bearerControlMode;
    @Getter
    private List<EventTrigger> eventTrigger;
    @Getter
    private List<ChargingRuleInstall> chargingRuleInstall;
    @Getter
    private List<ChargingRuleRemove> chargingRuleRemove;
    @Setter
    @Getter
    private Online online;
    @Setter
    @Getter
    private Offline offline;

    @Getter
    private List<QoSInformation> qoSInformation;
    @Setter
    @Getter
    private  RevalidationTime revalidationTime;
    @Setter
    @Getter
    private DefaultEpsBearerQoS defaultEpsBearerQoS;
    @Setter
    @Getter
    private BearerUsage bearerUsage;




    // called when received from socket
    public CreditControlAnswer(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public void setEventTrigger(EventTrigger eventTrigger) {
        if(this.eventTrigger == null){
          this.eventTrigger = new ArrayList<>();
        }
        this.eventTrigger.add(eventTrigger);
    }

    public void setChargingRuleInstall(ChargingRuleInstall chargingRuleInstall) {
        if(this.chargingRuleInstall == null){
            this.chargingRuleInstall = new ArrayList<>();
        }
        this.chargingRuleInstall.add(chargingRuleInstall);
    }

    public void setChargingRuleRemove(ChargingRuleRemove chargingRuleRemove) {
        if(this.chargingRuleRemove == null){
            this.chargingRuleRemove = new ArrayList<>();
        }
        this.chargingRuleRemove.add(chargingRuleRemove);
    }

    public void setQoSInformation(QoSInformation qoSInformation){
        if(this.qoSInformation == null){
            this.qoSInformation = new ArrayList<>();
        }
        this.qoSInformation.add(qoSInformation);
    }

    @Override
    public CreditControlAnswer decode(DiameterPacketHeader header, byte[] buffer) {
        return DiameterPacketDecoder.packetDecode(this);
    }

}
