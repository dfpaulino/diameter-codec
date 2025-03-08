package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.utils.ReadBytesUtils;

public class AccessNetworkChargingIdentifierGx extends Avp<AccessNetworkChargingIdentifierGx> {
    public static int avpCode = 1022;
    public static byte flags = (byte) 0x80;
    @Getter@Setter
    private AccessNetworkChargingIdentifierValue accessNetworkChargingIdentifierValue;

    private void setPriorityLevel(AccessNetworkChargingIdentifierValue accessNetworkChargingIdentifierValue) {
        this.accessNetworkChargingIdentifierValue = accessNetworkChargingIdentifierValue;
    }

    public AccessNetworkChargingIdentifierGx(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    @Override
    public AccessNetworkChargingIdentifierGx decode(byte[] buffer, int position, AvpHeader header) {
        return (AccessNetworkChargingIdentifierGx)AvpDecoders.GroupedAvpDecoder.decode(this,buffer,position,header);
    }
}
