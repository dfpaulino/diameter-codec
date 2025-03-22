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
    private DestinationRealm destinationRealm;
    @Setter
    @Getter
    private CcRequestType ccRequestType;
    @Setter
    @Getter
    private CcRequestNumber ccRequestNumber;
    @Setter
    @Getter
    private DestinationHost destinationHost;
    @Setter
    @Getter
    private OriginStateId originStateId;
    @Getter
    private List<SubscriptionId> subscriptionId;
    @Getter
    private List<SupportedFeatures> supportedFeatures;
    @Setter
    @Getter
    private NetworkRequestSupport networkRequestSupport;
    @Setter
    @Getter
    private BearerIdentifier bearerIdentifier;
    @Setter
    @Getter
    private BearerOperation bearerOperation;

    @Setter
    @Getter
    private FrameIpAddress frameIpAddress;

    @Setter
    @Getter
    private IpCanType ipCanType;
    @Setter
    @Getter
    private GppRatType gppRatType;
    @Setter
    @Getter
    private RatType ratType;
    @Setter
    @Getter
    private TerminationCause terminationCause;
    @Setter
    @Getter
    private UserEquipmentInfo userEquipmentInfo;
    @Setter
    @Getter
    private QoSInformation qoSInformation;
    @Setter
    @Getter
    private QoSNegotiation qoSNegotiation;
    @Setter
    @Getter
    private QoSUpgrade qoSUpgrade;
    @Setter
    @Getter
    private GppSgsnAddress gppSgsnAddress;
    @Setter
    @Getter
    private GppGgsnAddress gppGgsnAddress;
    @Setter
    @Getter
    private GppUserLocationInfo gppUserLocationInfo;
    @Setter
    @Getter
    private CalledStationId calledStationId;
    @Setter
    @Getter
    private BearerUsage bearerUsage;
    @Setter
    @Getter
    private AccessNetworkChargingAddress accessNetworkChargingAddress;
    @Getter
    private List<AccessNetworkChargingIdentifierGx> accessNetworkChargingIdentifierGx;
    @Setter
    @Getter
    private Online online;
    @Setter
    @Getter
    private Offline offline;


    // called when received from socket
    public CreditControlAnswer(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }



    @Override
    public CreditControlAnswer decode(DiameterPacketHeader header, byte[] buffer) {
        return DiameterPacketDecoder.packetDecode(this);
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
