package org.example.diameter.packet;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.common.*;
import org.example.diameter.packet.utils.DiameterPacketDecoder;

import java.util.ArrayList;
import java.util.List;

/*
Message Format

      <CER> ::= < Diameter Header: 257, REQ >
                { Origin-Host }
                { Origin-Realm }
             1* { Host-IP-Address }
                { Vendor-Id }
                { Product-Name }
                [ Origin-State-Id ]
              * [ Supported-Vendor-Id ]
              * [ Auth-Application-Id ]
              * [ Inband-Security-Id ]
              * [ Acct-Application-Id ]
              * [ Vendor-Specific-Application-Id ]
                [ Firmware-Revision ]
              * [ AVP ]
 */
public class CapabilitiesExchangeRequest extends DiameterPacket {
    // AVP definitions
    @Setter
    @Getter
    private OriginHost originHost;
    @Setter
    @Getter
    private OriginRealm originRealm;
    @Setter
    @Getter
    private HostIpAddress hostIpAddress;
    @Setter
    @Getter
    private VendorId vendorId;
    @Setter
    @Getter
    private ProductName productName;
    @Setter
    @Getter
    private OriginStateId originStateId;
    @Getter
    private List<AuthApplicationId> authApplicationId;
    @Getter
    private List<SupportedVendorId> supportedVendorId;
    @Getter
    private List<VendorSpecificApplicationId> vendorSpecificApplicationId;

    // called when received from socket
    public CapabilitiesExchangeRequest(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public CapabilitiesExchangeRequest() {
        super();
    }
    @Override
    public void  decode(DiameterPacketHeader header, byte[] buffer) {
        DiameterPacketDecoder.packetDecode(this);
    }



    public void setSupportedVendorId(SupportedVendorId supportedVendorId) {
        if (this.supportedVendorId == null) {
            this.supportedVendorId = new ArrayList<>(2);
        }
        this.supportedVendorId.add(supportedVendorId);
    }

    public void setAuthApplicationId(AuthApplicationId authApplicationId) {
        if (this.authApplicationId == null) {
            this.authApplicationId = new ArrayList<>(2);
        }
        this.authApplicationId.add(authApplicationId);
    }

    public void setVendorSpecificApplicationId(VendorSpecificApplicationId vendorSpecificApplicationId) {
        if (this.vendorSpecificApplicationId == null) {
            this.vendorSpecificApplicationId = new ArrayList<>(2);
        }
        this.vendorSpecificApplicationId.add(vendorSpecificApplicationId);
    }
}
