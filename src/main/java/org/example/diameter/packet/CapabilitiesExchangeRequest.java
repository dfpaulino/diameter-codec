package org.example.diameter.packet;

import lombok.Setter;
import org.example.diameter.avp.common.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class CapabilitiesExchangeRequest extends DiameterPacket<CapabilitiesExchangeRequest> {

    private static Map<Class<?>, Method> classToSetMethod;
    // AVP definitions
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
    private final List<SupportedVendorId> supportedVendorId = new ArrayList<>();
    private final List<VendorSpecificApplicationId> vendorSpecificApplicationId = new ArrayList<>();

    // called when received from socket
    public CapabilitiesExchangeRequest(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public void setSupportedVendorId(SupportedVendorId supportedVendorId) {
        this.supportedVendorId.add(supportedVendorId);
    }

    public void setSupportedVendorId(List<SupportedVendorId> supportedVendorId) {
        this.supportedVendorId.addAll(supportedVendorId);
    }

}
