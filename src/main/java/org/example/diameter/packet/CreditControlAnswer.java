package org.example.diameter.packet;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.InnerAvp;
import org.example.diameter.avp.common.*;
import org.example.diameter.avp.gx.*;
import org.example.diameter.packet.utils.DiameterPacketDecoder;
import org.example.diameter.packet.utils.DiameterPacketEncoder;

import java.util.ArrayList;
import java.util.List;

/*
Message Format
<CC-Answer> ::=
< Diameter Header: 272, PXY >
< Session-Id >
[ DRMP ]
{ Auth-Application-Id }
{ Origin-Host }
{ Origin-Realm }
[ Result-Code ]
[ Experimental-Result ]
{ CC-Request-Type }
{ CC-Request-Number }
[ OC-Supported-Features ]
[ OC-OLR ]
*[ Supported-Features ]
[ Bearer-Control-Mode ]
*[ Event-Trigger ]
[ Event-Report-Indication ]
[ Origin-State-Id ]
*[ Redirect-Host ]
[ Redirect-Host-Usage ]
[ Redirect-Max-Cache-Time ]
*[ Charging-Rule-Remove ]
*[ Charging-Rule-Install ]
[ Charging-Information ]
[ Online ]
[ Offline ]
*[ QoS-Information ]
[ Revalidation-Time ]
[ Default-EPS-Bearer-QoS ]
[ Default-QoS-Information ]
[ Bearer-Usage ]
*[ Usage-Monitoring-Information ]
*[ CSG-Information-Reporting ]
[ User-CSG-Information ]
[ PRA-Install ]
[ PRA-Remove ]
[ Presence-Reporting-Area-Information ]
[ Session-Release-Cause ]
[ NBIFOM-Support ]
[ NBIFOM-Mode ]
[ Default-Access ]
[ RAN-Rule-Support ]
*[ Routing-Rule-Report ]
0*4[ Conditional-Policy-Information ]
[ Removal-Of-Access ]
[ IP-CAN-Type ]
[ Error-Message ]
[ Error-Reporting-Host ]
[ Failed-AVP ]
*[ Proxy-Info ]
*[ Route-Record ]
*[ Load ]
*[ AVP ]
 */

public class CreditControlAnswer extends DiameterPacket {

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
    public void  decode(DiameterPacketHeader header, byte[] buffer) {
        DiameterPacketDecoder.packetDecode(this);
    }

    @Override
    public byte[] encode() {
        return DiameterPacketEncoder.encode(this);
    }
}
