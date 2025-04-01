package org.example.diameter.packet;

import lombok.Getter;
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
    @Getter@Setter
    private ResultCode resultCode;
    @Getter@Setter
    private OriginHost originHost;
    @Getter@Setter
    private OriginRealm originRealm;
    @Getter@Setter
    private HostIpAddress hostIpAddress;
    @Getter@Setter
    private VendorId vendorId;
    @Getter@Setter
    private ProductName productName;
    @Getter@Setter
    private OriginStateId originStateId;
    @Getter
    private List<AuthApplicationId> authApplicationId;
    @Getter
    private List<SupportedVendorId> supportedVendorId;
    @Getter
    private List<VendorSpecificApplicationId> vendorSpecificApplicationId = new ArrayList<>();

    // called when received from socket
    public CapabilitiesExchangeAnswer(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public CapabilitiesExchangeAnswer() {
        super();
    }

    @Override
    public CapabilitiesExchangeAnswer decode(DiameterPacketHeader header, byte[] buffer) {
        return DiameterPacketDecoder.packetDecode(this);
    }

    public void setSupportedVendorId(SupportedVendorId supportedVendorId) {
        if(this.supportedVendorId == null) {
            this.supportedVendorId = new ArrayList<>();
        }
        this.supportedVendorId.add(supportedVendorId);
    }

    public void setVendorSpecificApplicationId(VendorSpecificApplicationId vendorSpecificApplicationId) {
        if(this.vendorSpecificApplicationId == null) {
            this.vendorSpecificApplicationId = new ArrayList<>();
        }
        this.vendorSpecificApplicationId.add(vendorSpecificApplicationId);
    }

    public void setAuthApplicationId(AuthApplicationId authApplicationId) {
        if(this.authApplicationId == null) {
            this.authApplicationId = new ArrayList<>();
        }
        this.authApplicationId.add(authApplicationId);
    }




}
