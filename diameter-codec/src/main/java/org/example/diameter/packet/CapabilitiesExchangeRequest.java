package org.example.diameter.packet;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.InnerAvp;
import org.example.diameter.avp.common.*;
import org.example.diameter.packet.utils.DiameterPacketDecoder;
import org.example.diameter.packet.utils.DiameterPacketEncoder;

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
    @InnerAvp @Setter @Getter
    private OriginHost originHost;
    @InnerAvp @Setter @Getter
    private OriginRealm originRealm;
    @InnerAvp @Setter @Getter
    private HostIpAddress hostIpAddress;
    @InnerAvp @Setter @Getter
    private VendorId vendorId;
    @InnerAvp @Setter @Getter
    private ProductName productName;
    @InnerAvp @Setter @Getter
    private OriginStateId originStateId;
    @InnerAvp(isCollection = true) @Getter
    private List<AuthApplicationId> authApplicationId;
    @InnerAvp(isCollection = true) @Getter
    private List<SupportedVendorId> supportedVendorId;
    @InnerAvp(isCollection = true) @Getter
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

    @Override
    public byte[] encode() {
        return DiameterPacketEncoder.encode(this);
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
