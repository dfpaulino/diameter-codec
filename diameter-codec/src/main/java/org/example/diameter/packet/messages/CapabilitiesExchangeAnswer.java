package org.example.diameter.packet.messages;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.InnerAvp;
import org.example.diameter.avp.common.*;
import org.example.diameter.packet.DiameterPacket;
import org.example.diameter.packet.DiameterPacketHeader;
import org.example.diameter.packet.utils.DiameterPacketDecoder;
import org.example.diameter.packet.utils.DiameterPacketEncoder;

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
public class CapabilitiesExchangeAnswer extends DiameterPacket {

    // AVP definitions
    @InnerAvp @Setter @Getter
    private ResultCode resultCode;
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
    public CapabilitiesExchangeAnswer(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public CapabilitiesExchangeAnswer() {
        super();
    }

    @Override
    public void decode(DiameterPacketHeader header, byte[] buffer) {
        DiameterPacketDecoder.packetDecode(this);
    }

    @Override
    public byte[] encode() {
        return DiameterPacketEncoder.encode(this);
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
