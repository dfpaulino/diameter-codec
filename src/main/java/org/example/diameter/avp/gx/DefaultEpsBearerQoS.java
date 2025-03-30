package org.example.diameter.avp.gx;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.*;
import org.example.diameter.avp.enums.VendorId;
import org.example.diameter.utils.EncodeAvp;

@AvpRegister(avpCode =1049,avpBuilderMethod = "byteToAvp")
public class DefaultEpsBearerQoS extends Avp<DefaultEpsBearerQoS> {
    public static int avpCode = 1049;
    public static byte flags = (byte) 0xc0;
    @InnerAvp
    @Setter
    @Getter
    private QoSClassIdentifier qoSClassIdentifier;
    @InnerAvp@Getter@Setter
    private AllocationRetentionPriority allocationRetentionPriority;

    public DefaultEpsBearerQoS(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public DefaultEpsBearerQoS() {
        super(null);
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder(DefaultEpsBearerQoS::new);
    }

    @Override
    public DefaultEpsBearerQoS returnContent() {
        return this;
    }

    @Override
    public DefaultEpsBearerQoS decode(byte[] buffer, int position, AvpHeader header) {
        return (DefaultEpsBearerQoS) AvpTypeDecoders.GroupedAvpDecoder
                .decode(this,buffer,position,header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags, VendorId.GPP.getValue());
    }

}
