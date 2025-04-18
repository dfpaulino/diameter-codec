package org.example.diameter.packet.messages;

import lombok.Getter;
import lombok.Setter;
import org.example.diameter.avp.InnerAvp;
import org.example.diameter.avp.common.OriginHost;
import org.example.diameter.avp.common.OriginRealm;
import org.example.diameter.avp.common.OriginStateId;
import org.example.diameter.packet.DiameterPacket;
import org.example.diameter.packet.DiameterPacketHeader;
import org.example.diameter.packet.utils.DiameterPacketDecoder;
import org.example.diameter.packet.utils.DiameterPacketEncoder;

/*
Message Format

      <CER> ::= < Diameter Header: 280 >
                { Origin-Host }
                { Origin-Realm }
                [ Origin-State-Id ]

 */
public class DeviceWatchDogAnswer extends DiameterPacket {
    // AVP definitions
    @InnerAvp @Setter @Getter
    private OriginHost originHost;
    @InnerAvp @Setter @Getter
    private OriginRealm originRealm;
    @InnerAvp @Setter @Getter
    private OriginStateId originStateId;

    // called when received from socket
    public DeviceWatchDogAnswer(DiameterPacketHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public DeviceWatchDogAnswer() {
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
