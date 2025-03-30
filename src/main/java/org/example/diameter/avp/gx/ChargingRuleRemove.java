package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;

import java.util.ArrayList;
import java.util.List;

/*
Charging-Rule-Remove ::= < AVP Header: 1002 >
*[ Charging-Rule-Name ]
*[ Charging-Rule-Base-Name ]
*[ Required-Access-Info ]
[ Resource-Release-Notification ]
*[ AVP ]

 */
@AvpRegister(avpCode = 1002,avpBuilderMethod = "byteToAvp")
public class ChargingRuleRemove extends Avp<ChargingRuleRemove> {
    public static int avpCode = 1002;
    public static byte flags = (byte) 0xc0;

    @InnerAvp(isCollection = true)@Getter
    private List<ChargingRuleName> chargingRuleName ;
    @InnerAvp(isCollection = true)@Getter
    private List<ChargingRuleBaseName> chargingRuleBaseName  ;

    public ChargingRuleRemove(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public ChargingRuleRemove() {
        super(null);
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
        return new AvpBuilder(ChargingRuleRemove::new);
    }

    @Override
    public ChargingRuleRemove returnContent() {
        return this;
    }

    @Override
    public ChargingRuleRemove decode(byte[] buffer, int position, AvpHeader header) {
        return (ChargingRuleRemove) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags, VendorId.GPP.getValue());
    }

}
