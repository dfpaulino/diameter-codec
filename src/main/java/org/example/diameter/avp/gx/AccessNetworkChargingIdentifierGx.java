package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;

@AvpRegister(avpCode =1022,avpBuilderMethod = "byteToAvp")
public class AccessNetworkChargingIdentifierGx extends Avp<AccessNetworkChargingIdentifierGx> {
    public static int avpCode = 1022;
    public static byte flags = (byte) 0xc0;
    @InnerAvp@Getter@Setter
    private AccessNetworkChargingIdentifierValue accessNetworkChargingIdentifierValue;

    public AccessNetworkChargingIdentifierGx(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public AccessNetworkChargingIdentifierGx(AccessNetworkChargingIdentifierValue accessNetworkChargingIdentifierValue) {
        super(null);
        this.accessNetworkChargingIdentifierValue = accessNetworkChargingIdentifierValue;
    }

    @Override
    public AccessNetworkChargingIdentifierGx returnContent() {
        return this;
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(AccessNetworkChargingIdentifierGx::new);
    }
    @Override
    public AccessNetworkChargingIdentifierGx decode(byte[] buffer, int position, AvpHeader header) {
        return (AccessNetworkChargingIdentifierGx) AvpTypeDecoders.GroupedAvpDecoder.decode(this,buffer,position,header);
    }
    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags, VendorId.GPP.getValue());
    }
}
