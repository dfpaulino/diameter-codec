package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.avp.common.RatingGroup;
import org.example.diameter.avp.common.ServiceIdentifier;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;

import java.util.ArrayList;
import java.util.List;

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
    @InnerAvp@Setter@Getter
    private ChargingRuleName chargingRuleName;
    @InnerAvp@Getter@Setter
    private ServiceIdentifier serviceIdentifier;
    @InnerAvp@Getter@Setter
    private RatingGroup ratingGroup;
    @InnerAvp(isCollection = true)@Getter
    private List<FlowInformation> flowInformation;
    @InnerAvp@Getter@Setter
    private FlowStatus flowStatus;
    @InnerAvp@Getter@Setter
    private QoSInformation qoSInformation;
    @InnerAvp@Getter@Setter
    private MeteringMethod meteringMethod;
    @InnerAvp@Getter@Setter
    private Precedence precedence;
    @InnerAvp@Getter@Setter
    private Online online;
    @InnerAvp@Getter@Setter
    private Offline offline;
    private MonitoringKey monitoringKey;


    public ChargingRuleDefinition(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public ChargingRuleDefinition() {
        super(null);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(ChargingRuleDefinition::new);
    }

    public void setFlowInformation(FlowInformation flowInformation) {
        if(this.flowInformation == null){
            this.flowInformation = new ArrayList<>();
        }
        this.flowInformation.add(flowInformation);
    }

    @Override
    public ChargingRuleDefinition returnContent() {
        return this;
    }

    @Override
    public ChargingRuleDefinition decode(byte[] buffer, int position, AvpHeader header) {
        return (ChargingRuleDefinition) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags, VendorId.GPP.getValue());
    }

}
