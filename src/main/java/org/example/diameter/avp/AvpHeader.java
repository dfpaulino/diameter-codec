package org.example.diameter.avp;

import org.immutables.value.Value;

import java.util.Optional;

/*


4.1.  AVP Header
Each AVP of type OctetString MUST be padded to align on a 32-bit
   boundary, while other AVP types align naturally.  A number of zero-
   valued bytes are added to the end of the AVP Data field till a word
   boundary is reached.  The length of the padding is not reflected in
   the AVP Length field.


   The fields in the AVP header MUST be sent in network byte order.  The
   format of the header is:

    0                   1                   2                   3
    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                           AVP Code                            |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |V M P r r r r r|                  AVP Length                   |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                        Vendor-ID (opt)                        |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |    Data ...
   +-+-+-+-+-+-+-+-+
 */
@Value.Immutable
public abstract class AvpHeader {
    public abstract int getAvpCode();

    public abstract byte getAvpFlags();

    public abstract int getAvpLength();

    public abstract Optional<Integer> getVendorId();

    @Value.Default
    public boolean isVendorSpecific() {
        return (getAvpFlags() & 0x80) == 0x80;
    }

    @Value.Default
    public int getPaddingSize() {
        if (getAvpLength() % 4 > 0) {
            return 4 - getAvpLength() % 4;
        } else {
            return 0;
        }
    }

}
