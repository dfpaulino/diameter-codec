package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.avp.common.RatingGroup;
import org.example.diameter.avp.common.ServiceIdentifier;

import java.util.ArrayList;

/*
Charging-Rule-Definition ::= < AVP Header: 1003 >
{ Charging-Rule-Name }
[ Service-Identifier ]
[ Rating-Group ]
*[ Flow-Information ]
[ Default-Bearer-Indication ]
[ TDF-Application-Identifier ]
[ Flow-Status ]
[ QoS-Information ]
[ PS-to-CS-Session-Continuity ]
[ Reporting-Level ]
[ Online ]
[ Offline ]
[ Max-PLR-DL ]
[ Max-PLR-UL ]
[ Metering-Method ]
[ Precedence ]
[ AF-Charging-Identifier ]
*[ Flows ]
[ Monitoring-Key]
[ Redirect-Information ]
[ Mute-Notification ]
[ AF-Signalling-Protocol ]
[ Sponsor-Identity ]
[ Application-Service-Provider-Identity ]
*[ Required-Access-Info ]
[ Sharing-Key-DL ]
[ Sharing-Key-UL ]
[ Traffic-Steering-Policy-Identifier-DL ]
[ Traffic-Steering-Policy-Identifier-UL ]
[ Content-Version ]
*[ AVP ]

 */
@AvpRegister(avpCode = 1003,avpBuilderMethod = "byteToAvp")
public class ChargingRuleDefinition extends Avp<ChargingRuleDefinition> {
    public static int avpCode = 1003;
    public static byte flags = (byte) 0xc0;
    @Setter@Getter
    private ChargingRuleName chargingRuleName;
    @Getter@Setter
    private ServiceIdentifier serviceIdentifier;
    @Getter@Setter
    private RatingGroup ratingGroup;
    //private List<FlowInformation> flowInformation;
    //@Getter@Setter
    //private FlowStatus flowStatus;
    @Getter@Setter
    private QoSInformation qoSInformation;
    @Getter@Setter
    private Online online;
    @Getter@Setter
    private Offline offline;
    @Getter@Setter
    private MeteringMethod meteringMethod;
    @Getter@Setter
    private MonitoringKey monitoringKey;
    @Getter@Setter
    private Precedence precedence;

    public ChargingRuleDefinition(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }


    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(ChargingRuleDefinition::new);
    }

    @Override
    public ChargingRuleDefinition decode(byte[] buffer, int position, AvpHeader header) {
        return (ChargingRuleDefinition) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }
    @Override
    public ChargingRuleDefinition returnContent() {
        return this;
    }

}
