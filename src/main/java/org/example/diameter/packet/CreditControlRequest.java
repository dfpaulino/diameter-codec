package org.example.diameter.packet;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.InnerAvp;
import org.example.diameter.avp.common.*;
import org.example.diameter.avp.gx.*;
import org.example.diameter.avp.rx.AccessNetworkChargingAddress;
import org.example.diameter.packet.utils.DiameterPacketDecoder;

import java.util.ArrayList;
import java.util.List;

/*
Message Format

 <CC-Request> ::= < Diameter Header: 272, REQ, PXY >
< Session-Id >
{ Auth-Application-Id }
{ Origin-Host }
{ Origin-Realm }
{ Destination-Realm }
{ CC-Request-Type }
{ CC-Request-Number }

[ Destination-Host ]
[ Origin-State-Id ]
*[ Subscription-Id ]
X[ OC-Supported-Features ]
*[ Supported-Features ]
X[ TDF-Information ]
[ Network-Request-Support ]
X*[ Packet-Filter-Information ]
X[ Packet-Filter-Operation ]
[ Bearer-Identifier ]
[ Bearer-Operation ]
X[ Dynamic-Address-Flag ]
X[ Dynamic-Address-Flag-Extension ]
X[ PDN-Connection-Charging-ID ]
[ Framed-IP-Address ]
X[ Framed-Ipv6-Prefix ]
[ IP-CAN-Type ]
[ 3GPP-RAT-Type ]
X[ AN-Trusted ]
[ RAT-Type ]
[ Termination-Cause ]
[ User-Equipment-Info ]
[ QoS-Information ]
[ QoS-Negotiation ]
X[ QoS-Upgrade ]
X[ Default-EPS-Bearer-QoS ]
X[ Default-QoS-Information ]
X0*2 [ AN-GW-Address ]
X[ AN-GW-Status ]
X[ 3GPP-SGSN-MCC-MNC ]
[ 3GPP-SGSN-Address ]
X[ 3GPP-SGSN-Ipv6-Address ]
[ 3GPP-GGSN-Address ]
X[ 3GPP-GGSN-Ipv6-Address ]
X[ 3GPP-Selection-Mode ]
X[ RAI ]
[ 3GPP-User-Location-Info ]
X[ Fixed-User-Location-Info ]
X[ User-Location-Info-Time ]
X[ User-CSG-Information ]
X[ TWAN-Identifier ]
X[ 3GPP-MS-TimeZone ]
X*[ RAN-NAS-Release-Cause ]
X[ 3GPP-Charging-Characteristics ]
[ Called-Station-Id ]
X[ PDN-Connection-ID ]
[ Bearer-Usage ]
[ Online ]
[ Offline ]
X*[ TFT-Packet-Filter-Information ]
X*[ Charging-Rule-Report ]
X*[ Application-Detection-Information ]
X*[ Event-Trigger ]
X[ Event-Report-Indication ]
[ Access-Network-Charging-Address ]
*[ Access-Network-Charging-Identifier-Gx ]
X*[ CoA-Information ]
X*[ Usage-Monitoring-Information ]
X[ NBIFOM-Support ]
X[ NBIFOM-Mode ]
X[ Default-Access ]
X[ Origination-Time-Stamp ]
X[ Maximum-Wait-Time ]
X[ Access-Availability-Change-Reason ]
X[ Routing-Rule-Install ]
X[ Routing-Rule-Remove ]
X[ HeNB-Local-IP-Address ]
X[ UE-Local-IP-Address ]
X[ UDP-Source-Port ]
X[ TCP-Source-Port ]
X*[ Presence-Reporting-Area-Information ]
X[ Logical-Access-ID ]
X[ Physical-Access-ID ]
X*[ Proxy-Info ]
X*[ Route-Record ]
X[ 3GPP-PS-Data-Off-Status ]
*[ AVP ]
 */

public class CreditControlRequest extends DiameterPacket {

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
    private DestinationRealm destinationRealm;
    @InnerAvp @Setter @Getter
    private CcRequestType ccRequestType;
    @InnerAvp @Setter @Getter
    private CcRequestNumber ccRequestNumber;
    @InnerAvp @Setter @Getter
    private DestinationHost destinationHost;
    @InnerAvp @Setter @Getter
    private OriginStateId originStateId;
    @InnerAvp(isCollection = true) @Getter
    private List<SubscriptionId> subscriptionId;
    @InnerAvp(isCollection = true) @Getter
    private List<SupportedFeatures> supportedFeatures;
    @InnerAvp @Setter @Getter
    private NetworkRequestSupport networkRequestSupport;
    @InnerAvp @Setter @Getter
    private BearerIdentifier bearerIdentifier;
    @InnerAvp @Setter @Getter
    private BearerOperation bearerOperation;
    @InnerAvp @Setter @Getter
    private FrameIpAddress frameIpAddress;
    @InnerAvp @Setter @Getter
    private IpCanType ipCanType;
    @InnerAvp @Setter @Getter
    private GppRatType gppRatType;
    @InnerAvp @Setter @Getter
    private RatType ratType;
    @InnerAvp @Setter @Getter
    private TerminationCause terminationCause;
    @InnerAvp @Setter @Getter
    private UserEquipmentInfo userEquipmentInfo;
    @InnerAvp @Setter @Getter
    private QoSInformation qoSInformation;
    @InnerAvp @Setter @Getter
    private QoSNegotiation qoSNegotiation;
    @InnerAvp @Setter @Getter
    private QoSUpgrade qoSUpgrade;
    @InnerAvp @Setter @Getter
    private GppSgsnAddress gppSgsnAddress;
    @InnerAvp @Setter @Getter
    private GppGgsnAddress gppGgsnAddress;
    @InnerAvp @Setter @Getter
    private GppUserLocationInfo gppUserLocationInfo;
    @InnerAvp @Setter @Getter
    private CalledStationId calledStationId;
    @InnerAvp @Setter @Getter
    private BearerUsage bearerUsage;
    @InnerAvp @Setter @Getter
    private AccessNetworkChargingAddress accessNetworkChargingAddress;
    @InnerAvp(isCollection = true) @Getter
    private List<AccessNetworkChargingIdentifierGx> accessNetworkChargingIdentifierGx;
    @InnerAvp @Setter @Getter
    private Online online;
    @InnerAvp @Setter @Getter
    private Offline offline;
    @InnerAvp @Setter @Getter
    private EventTrigger eventTrigger;
    @InnerAvp @Setter @Getter
    private DefaultEpsBearerQoS defaultEpsBearerQoS;


    // called when received from socket
    public CreditControlRequest(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public CreditControlRequest() {
    }

    @Override
    public void decode(DiameterPacketHeader header, byte[] buffer) {
        DiameterPacketDecoder.packetDecode(this);
    }

    public void setAccessNetworkChargingIdentifierGx(AccessNetworkChargingIdentifierGx accessNetworkChargingIdentifierGx) {
        if (this.accessNetworkChargingIdentifierGx == null) {
            this.accessNetworkChargingIdentifierGx = new ArrayList<>(2);
        }
        this.accessNetworkChargingIdentifierGx.add(accessNetworkChargingIdentifierGx);
    }

    public void setSupportedFeatures(SupportedFeatures supportedFeatures) {
        if (this.supportedFeatures == null) {
            this.supportedFeatures = new ArrayList<>(2);
        }
        this.supportedFeatures.add(supportedFeatures);
    }

    public void setSubscriptionId(SubscriptionId subscriptionId) {
        if (this.subscriptionId == null) {
            this.subscriptionId = new ArrayList<>(2);
        }
        this.subscriptionId.add(subscriptionId);
    }
}
