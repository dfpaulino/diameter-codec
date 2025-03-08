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
X[ 3GPP-GGSN-Address ]
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
X[ Online ]
X[ Offline ]
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

public class CreditControlRequest extends DiameterPacket<CreditControlRequest> {

    // AVP definitions
    @Setter@Getter
    private SessionId sessionId;
    @Setter@Getter
    private AuthApplicationId authApplicationId;
    @Setter@Getter
    private OriginHost originHost;
    @Setter@Getter
    private OriginRealm originRealm;
    @Setter@Getter
    private DestinationRealm destinationRealm;
    @Setter@Getter
    private CcRequestType ccRequestType;
    @Setter@Getter
    private CcRequestNumber ccRequestNumber;
    @Setter@Getter
    private DestinationHost destinationHost;
    @Setter@Getter
    private OriginStateId originStateId;
    @Getter
    private List<SubscriptionId> subscriptionId;
    @Getter
    private List<SupportedFeatures> supportedFeatures;
    @Setter@Getter
    private NetworkRequestSupport networkRequestSupport;
    @Setter@Getter
    private BearerIdentifier bearerIdentifier;
    @Setter@Getter
    private BearerOperation bearerOperation;

    @Setter@Getter
    private FrameIpAddress frameIpAddress;

    @Setter@Getter
    private IpCanType ipCanType;
    @Setter@Getter
    private GppRatType gppRatType;
    @Setter@Getter
    private RatType ratType;
    @Setter@Getter
    private TerminationCause terminationCause;
    @Setter@Getter
    private UserEquipmentInfo userEquipmentInfo;
    @Setter@Getter
    private QoSInformation qoSInformation;
    @Setter@Getter
    private QoSNegotiation qoSNegotiation;
    @Setter@Getter
    private QoSUpgrade qoSUpgrade;
    @Setter@Getter
    private GppSgsnAddress gppSgsnAddress;
    @Setter@Getter
    private GppUserLocationInfo gppUserLocationInfo;
    @Setter@Getter
    private CalledStationId calledStationId;
    @Setter@Getter
    private BearerUsage bearerUsage;
    @Setter@Getter
    private AccessNetworkChargingAddress accessNetworkChargingAddress;
    @Getter
    private List<AccessNetworkChargingIdentifierGx> accessNetworkChargingIdentifierGx;



    // called when received from socket
    public CreditControlRequest(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    @Override
    public CreditControlRequest decode(DiameterPacketHeader header, byte[] buffer) {
        return DiameterPacketDecoder.<CreditControlRequest>packetDecode(this);
    }

    public void setAccessNetworkChargingIdentifierGx(AccessNetworkChargingIdentifierGx accessNetworkChargingIdentifierGx) {
        if(this.accessNetworkChargingIdentifierGx==null){
            this.accessNetworkChargingIdentifierGx = new ArrayList<>(2);
        }
        this.accessNetworkChargingIdentifierGx.add(accessNetworkChargingIdentifierGx);
    }

    public void setSupportedFeatures(SupportedFeatures supportedFeatures) {
        if (this.supportedFeatures==null){
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
