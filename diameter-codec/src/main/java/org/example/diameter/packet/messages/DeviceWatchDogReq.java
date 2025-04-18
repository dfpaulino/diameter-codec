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

      <DWR> ::= < Diameter Header: 280, REQ >
                { Origin-Host }
                { Origin-Realm }
                [ Origin-State-Id ]

 */
public class DeviceWatchDogReq extends DiameterPacket {
    // AVP definitions
    @InnerAvp @Setter @Getter
    private OriginHost originHost;
    @InnerAvp @Setter @Getter
    private OriginRealm originRealm;
    @InnerAvp @Setter @Getter
    private OriginStateId originStateId;

    // called when received from socket
    public DeviceWatchDogReq(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public DeviceWatchDogReq() {
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


}
