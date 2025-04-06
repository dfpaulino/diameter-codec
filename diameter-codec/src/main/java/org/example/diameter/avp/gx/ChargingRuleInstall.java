package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;

import java.util.ArrayList;
import java.util.List;

/*
Charging-Rule-Install ::=< AVP Header: 1001 >
        *[ Charging-Rule-Definition ]
        *[ Charging-Rule-Name ]
        *[ Charging-Rule-Base-Name ]
        [ Bearer-Identifier ]
        [ Monitoring-Flags ]
        [ Rule-Activation-Time ]
        [ Rule-Deactivation-Time ]
        [ Resource-Allocation-Notification ]
        [ Charging-Correlation-Indicator ]
        [ IP-CAN-Type ]
        *[ AVP ]

 */
@AvpRegister(avpCode = 1001,avpBuilderMethod = "byteToAvp")
public class ChargingRuleInstall extends Avp<ChargingRuleInstall> {
    public static int avpCode = 1001;
    public static byte flags = (byte) 0xc0;
    @InnerAvp(isCollection = true)@Getter
    private List<ChargingRuleDefinition> chargingRuleDefinition ;
    @InnerAvp(isCollection = true)@Getter
    private List<ChargingRuleName> chargingRuleName ;
    @InnerAvp(isCollection = true)@Getter
    private List<ChargingRuleBaseName> chargingRuleBaseName  ;
    @InnerAvp@Getter@Setter
    private BearerIdentifier bearerIdentifier;
    @InnerAvp@Getter@Setter
    private MonitoringFlags monitoringFlags;
    @InnerAvp@Getter@Setter
    private RuleActivationTime ruleActivationTime;
    @InnerAvp@Getter@Setter
    private RuleDeactivationTime ruleDeactivationTime;
    @InnerAvp@Getter@Setter
    private IpCanType ipCanType;

    public ChargingRuleInstall(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public ChargingRuleInstall() {
        super(null);
    }

    public void setChargingRuleDefinition(ChargingRuleDefinition chargingRuleDefinition) {
        if(this.chargingRuleDefinition==null){
            this.chargingRuleDefinition = new ArrayList<>();
        }
        this.chargingRuleDefinition.add(chargingRuleDefinition);
    }

    public void setChargingRuleName(ChargingRuleName chargingRuleName) {
        if(this.chargingRuleName==null){
            this.chargingRuleName = new ArrayList<>();
        }
        this.chargingRuleName.add(chargingRuleName);
    }

    public void setChargingRuleBaseName(ChargingRuleBaseName chargingRuleBaseName) {
        if(this.chargingRuleBaseName==null){
            this.chargingRuleBaseName = new ArrayList<>();
        }
        this.chargingRuleBaseName.add(chargingRuleBaseName);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(ChargingRuleInstall::new);
    }

    @Override
    public ChargingRuleInstall returnContent() {
        return this;
    }

    @Override
    public ChargingRuleInstall decode(byte[] buffer, int position, AvpHeader header) {
        return (ChargingRuleInstall) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags, VendorId.GPP.getValue());
    }

}
