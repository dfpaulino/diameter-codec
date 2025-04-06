package org.example.diameter.avp;

import org.example.diameter.avp.types.Address;
import org.example.diameter.avp.types.Time;
import org.example.diameter.utils.ReadAvpHeader;
import org.example.diameter.utils.ReadBytesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class AvpTypeDecoders {

    private static final Logger logger = LoggerFactory.getLogger(AvpTypeDecoders.class);
    private static final ByteToAvpMapper byteToAvpMapper = ByteToAvpMapper.getInstance();
    private static final long SEC_1900_1970 = 2208988800L;

    public static AvpTypeDecoder<String> OctectStringUTF8Decoder = (byte[] buffer, int position, AvpHeader header) -> {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int headerSize = (header.isVendorSpecific() ? 12 : 8);
        int offset = headerSize + position;
        int dataLen = (header.getAvpLength() - headerSize);
        // AVP of type Octet os padded to be multiple of 4 octets
        return new String(buffer, offset, dataLen, StandardCharsets.UTF_8);
    };

    public static AvpTypeDecoder<byte[]> OctetStringDecoder = (byte[] buffer, int position, AvpHeader header) -> {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific() ? 12 : 8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int len = header.getAvpLength() - (header.isVendorSpecific() ? 12 : 8);
        int lastPos = position + header.getAvpLength() + header.getPaddingSize();
        return ReadBytesUtils.readNBytesAsByteArray(buffer, offset, len);
    };

    public static AvpTypeDecoder<Integer> Integer32Decoder = (byte[] buffer, int position, AvpHeader header) -> {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific() ? 12 : 8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        return ReadBytesUtils.readNBytesAsInt(buffer, offset, 4);
    };

    public static AvpTypeDecoder<Time> ntpTimeDecoder = (byte[] buffer, int position, AvpHeader header) -> {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific() ? 12 : 8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        return new Time(ReadBytesUtils.readNBytesAsByteArray(buffer, offset, 4));
    };

    // TODO
    public static AvpTypeDecoder<Address> AddressDecoder = (byte[] buffer, int position, AvpHeader header) -> {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific() ? 12 : 8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        short addrFamily = (short) ((buffer[offset] & 0xff) << 8 | (buffer[offset + 1] & 0xff));
        String ipStr;
        int pos = offset + 2;
        if (addrFamily == 1) {
            //ipv4 are 4 bytes
            ipStr = (buffer[pos] & 0xff) + "." + (buffer[pos + 1] & 0xff) + "." + (buffer[pos + 2] & 0xff) + "." + (buffer[pos + 3] & 0xff);
        } else {
            /*
            An IPv6 address is 128 bits in length and is written as
            eight groups of four hexadecimal digits.
            Each group is separated from the others by colons (:)
             */
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                if(i%2==0 && i>0){
                    sb.append(":");
                }
                sb.append((HexFormat.of().toHexDigits(buffer[pos+i])));
            }
            ipStr = sb.toString();
        }
        return new Address(addrFamily, ipStr);
    };


    public static GroupedAvpDecoder<Avp<?>> GroupedAvpDecoder = (Avp<?> self, byte[] buffer, int position, AvpHeader header) -> {
        //skip Group header and position to the "data" where nester AVP are present
        // All Avps are multiple of 4 octets...OctectString Avp, avpLen does not reflect padding!!
        int avpLen = header.getAvpLength() + header.getPaddingSize();
        int offset = position + (header.isVendorSpecific() ? 12 : 8);
        int index = offset;
        // loop until we reached the end of the Grouped AVP
        while (index < (position + avpLen)) {
            AvpHeader localAvpheader = ReadAvpHeader.readAvpHeaderFromBytes(buffer, index);
            AvpBuilder avpBuilder = byteToAvpMapper.getAvpBuilderMapper().get(localAvpheader.getAvpCode());

            if (null != avpBuilder) {
                Avp<?> avp = avpBuilder.builder().createAvp(localAvpheader, buffer, index);

                String callMethod = "set" + avp.getClass().getSimpleName();
                try {
                    Method method = self.getClass().getDeclaredMethod(callMethod, avp.getClass());
                    method.setAccessible(true);
                    method.invoke(self, avp);
                    method.setAccessible(false);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    logger.warn("AVP [{}] seems does not belong to this group... ignoring", avp.getClass().getSimpleName());
                    logger.warn("Exception {}", e);
                }
            } else {
                logger.warn("Unknown AVP ID [{}]", localAvpheader.getAvpCode());
            }
            // move to next AVP based on current AVP len...and padding. All AVPS must be multiple of 4 octets
            index += localAvpheader.getAvpLength() + localAvpheader.getPaddingSize();
        }
        return self;
    };
}
