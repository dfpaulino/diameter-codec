package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;

@AvpRegister(avpCode =1022,avpBuilderMethod = "byteToAvp")
public class AccessNetworkChargingIdentifierGx extends Avp<AccessNetworkChargingIdentifierGx> {
    public static int avpCode = 1022;
    public static byte flags = (byte) 0x80;
    @Getter@Setter
    private AccessNetworkChargingIdentifierValue accessNetworkChargingIdentifierValue;

    public AccessNetworkChargingIdentifierGx(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }
    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(AccessNetworkChargingIdentifierGx::new);
    }
    @Override
    public AccessNetworkChargingIdentifierGx decode(byte[] buffer, int position, AvpHeader header) {
        return (AccessNetworkChargingIdentifierGx) AvpTypeDecoders.GroupedAvpDecoder.decode(this,buffer,position,header);
    }
}
