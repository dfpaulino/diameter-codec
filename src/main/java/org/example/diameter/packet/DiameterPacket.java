package org.example.diameter.packet;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
.  Diameter Header

   A summary of the Diameter header format is shown below.  The fields
   are transmitted in network byte order.

    0                   1                   2                   3
    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |    Version    |                 Message Length                |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   | command flags |                  Command-Code                 |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                         Application-ID                        |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                      Hop-by-Hop Identifier                    |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                      End-to-End Identifier                    |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |  AVPs ...
   +-+-+-+-+-+-+-+-+-+-+-+-+
 */
// Global Diamter Packet

public abstract class DiameterPacket<T> {
    private static final Logger logger = LoggerFactory.getLogger(DiameterPacket.class);
    @Getter
    private DiameterPacketHeader header;
    // The whole data as byte[]
    private byte[] buffer;
    // object data (decoded format)
    private T data;

    public DiameterPacket(DiameterPacketHeader header, byte[] buffer) {
        this.header = header;
        this.buffer = buffer;
        // lazy decoding
        this.data = this.decode(header, buffer);
    }

    public DiameterPacket() {
    }

    public T getData() {
        if (this.data == null && buffer!=null) {
            this.data = this.decode(this.header, this.buffer);
        }
        return this.data;
    }

    public byte[] getBuffer() {
        if (this.buffer.length == 0) {
            this.buffer = this.encode();
        }
        return buffer;
    }

    public abstract T decode(DiameterPacketHeader header, byte[] buffer);

    private byte[] encode() {
        return new byte[1024];
    }
}