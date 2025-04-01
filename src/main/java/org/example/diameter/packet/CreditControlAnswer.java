package org.example.diameter.packet;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.InnerAvp;
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
    @InnerAvp @Setter @Getter
    private SessionId sessionId;
    @InnerAvp @Setter @Getter
    private AuthApplicationId authApplicationId;
    @InnerAvp @Setter @Getter
    private OriginHost originHost;
    @InnerAvp @Setter @Getter
    private OriginRealm originRealm;
    @InnerAvp @Setter @Getter
    private ResultCode resultCode;
    @InnerAvp @Setter @Getter
    private CcRequestType ccRequestType;
    @InnerAvp @Setter @Getter
    private CcRequestNumber ccRequestNumber;
    @InnerAvp @Setter @Getter
    private OriginStateId originStateId;
    @InnerAvp @Setter @Getter
    private BearerControlMode bearerControlMode;
    @InnerAvp(isCollection = true) @Getter
    private List<EventTrigger> eventTrigger;
    @InnerAvp(isCollection = true) @Getter
    private List<ChargingRuleInstall> chargingRuleInstall;
    @InnerAvp(isCollection = true) @Getter
    private List<ChargingRuleRemove> chargingRuleRemove;
    @InnerAvp @Getter @Setter
    private Online online;
    @InnerAvp @Getter @Setter
    private Offline offline;

    @InnerAvp(isCollection = true) @Getter
    private List<QoSInformation> qoSInformation;
    @InnerAvp @Getter @Setter
    private  RevalidationTime revalidationTime;
    @InnerAvp @Getter @Setter
    private DefaultEpsBearerQoS defaultEpsBearerQoS;
    @InnerAvp @Getter @Setter
    private BearerUsage bearerUsage;

    // called when received from socket
    public CreditControlAnswer(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public CreditControlAnswer() {
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
