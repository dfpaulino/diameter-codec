package org.example.diameter.avp;

import org.example.diameter.Utils.ReadAvpHeader;
import org.example.diameter.Utils.ReadBytesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class AvpDecoders {

    private static final Logger logger = LoggerFactory.getLogger(AvpDecoders.class);

    public static AvpDecoder<String> OctectStringUTF8Decoder = (byte[] buffer, int position, AvpHeader header) -> {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int headerSize = (header.isVendorSpecific() ? 12 : 8);
        int offset = headerSize + position;
        int dataLen = (header.getAvpLength() - headerSize);
        // AVP of type Octet os padded to be multiple of 4 octets
        return new String(buffer, offset, dataLen, StandardCharsets.UTF_8);
    };

    public static AvpDecoder<byte[]> OctetStringDecoder = (byte[] buffer, int position, AvpHeader header) -> {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific() ? 12 : 8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int len = header.getAvpLength() - offset;
        return ReadBytesUtils.readNBytesAsByteArray(buffer, offset, len);
    };

    public static AvpDecoder<Integer> Integer32Decoder = (byte[] buffer, int position, AvpHeader header) -> {
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific() ? 12 : 8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        return ReadBytesUtils.readNBytesAsInt(buffer, offset, 4);
    };


    public static GroupedAvpDecoder<Avp<?>> GroupedAvpDecoder = (Avp<?> self, byte[] buffer, int position, AvpHeader header) -> {
        //skip Group header and position to the "data" where nester AVP are present
        // All Avps are multiple of 4 octets...OctectString Avp, avpLen does not reflect padding!!
        int avpLen = header.getAvpLength() + header.getPaddingSize();
        int offset = position + (header.isVendorSpecific() ? 12 : 8);
        int index = position + offset;
        // loop until we reached the end of the Grouped AVP
        while (index < (position + avpLen)) {
            AvpHeader localAvpheader = ReadAvpHeader.readAvpHeaderFromBytes(buffer, index);
            AvpIdToAvpMapper.AvpDefinition avpDefinition = AvpIdToAvpMapper.getAvpMapper()
                    .get(localAvpheader.getAvpCode());
            if (null != avpDefinition) {
                Avp<?> avp = avpDefinition.avpCreator.createInstance(localAvpheader, buffer, index);
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
