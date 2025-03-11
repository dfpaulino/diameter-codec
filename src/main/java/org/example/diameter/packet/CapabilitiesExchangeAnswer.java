package org.example.diameter.packet;

import lombok.Setter;
import org.example.diameter.avp.common.*;

import java.util.ArrayList;
import java.util.List;

/*
Message Format

     <CEA> ::= < Diameter Header: 257 >
                { Result-Code }
                { Origin-Host }
                { Origin-Realm }
             1* { Host-IP-Address }
                { Vendor-Id }
                { Product-Name }
                [ Origin-State-Id ]
                [ Error-Message ]
              * [ Failed-AVP ]
              * [ Supported-Vendor-Id ]
              * [ Auth-Application-Id ]
              * [ Inband-Security-Id ]
              * [ Acct-Application-Id ]
              * [ Vendor-Specific-Application-Id ]
                [ Firmware-Revision ]
              * [ AVP ]
 */
public class CapabilitiesExchangeAnswer extends DiameterPacket<CapabilitiesExchangeAnswer> {

    // AVP definitions
    @Setter
    private ResultCode resultCode;
    @Setter
    private OriginHost originHost;
    @Setter
    private OriginRealm originRealm;
    @Setter
    private HostIpAddress hostIpAddress;
    @Setter
    private VendorId vendorId;
    @Setter
    private ProductName productName;
    @Setter
    private OriginStateId originStateId;

    private final List<AuthApplicationId> authApplicationId = new ArrayList<>();
    private final List<SupportedVendorId> supportedVendorId = new ArrayList<>();
    private final List<VendorSpecificApplicationId> vendorSpecificApplicationId = new ArrayList<>();

    // called when received from socket
    public CapabilitiesExchangeAnswer(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    @Override
    public CapabilitiesExchangeAnswer decode(DiameterPacketHeader header, byte[] buffer) {
        return DiameterPacketDecoder.packetDecode(this);
    }

    public void setSupportedVendorId(SupportedVendorId supportedVendorId) {
        this.supportedVendorId.add(supportedVendorId);
    }

    public void setSupportedVendorId(List<SupportedVendorId> supportedVendorId) {
        this.supportedVendorId.addAll(supportedVendorId);
    }


}
