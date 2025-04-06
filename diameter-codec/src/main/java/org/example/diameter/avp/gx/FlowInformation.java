package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.avp.common.RatingGroup;
import org.example.diameter.avp.common.ServiceIdentifier;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;

import java.util.ArrayList;

/*
Flow-Information ::= < AVP Header: 1058 >
[ Flow-Description ]
[ Packet-Filter-Identifier ]
[ Packet-Filter-Usage ]
[ ToS-Traffic-Class ]
[ Security-Parameter-Index ]
[ Flow-Label ]
[ Flow-Direction ]
[ Routing-Rule-Identifier ]
*[ AVP ]
 */
@AvpRegister(avpCode = 1058,avpBuilderMethod = "byteToAvp")
public class FlowInformation extends Avp<FlowInformation> {
    public static int avpCode = 1058;
    public static byte flags = (byte) 0xc0;
    @InnerAvp@Setter@Getter
    private FlowDescription flowDescription;
    @InnerAvp@Getter@Setter
    private TosTrafficClass tosTrafficClass;
    @InnerAvp@Getter@Setter
    private FlowDirection flowDirection;

    public FlowInformation(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public FlowInformation() {
        super(null);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(FlowInformation::new);
    }

    @Override
    public FlowInformation returnContent() {
        return this;
    }

    @Override
    public FlowInformation decode(byte[] buffer, int position, AvpHeader header) {
        return (FlowInformation) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags, VendorId.GPP.getValue());
    }

}
