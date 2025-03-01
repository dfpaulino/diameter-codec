package org.example.diameter.avp;

import org.example.diameter.Utils.ReadAvpHeader;
import org.example.diameter.Utils.ReadBytesUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class AvpDecoders {
    public static AvpDecoder<String> OctectStringDecoder =(byte[] buffer,int position,AvpHeader header)->{
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int headerSize = (header.isVendorSpecific()?12:8);
        int offset = headerSize+position;
        int dataLen = (header.getAvpLength() - headerSize);
        // AVP of type Octet os padded to be multiple of 4 octets
        return new String(buffer,offset,dataLen, StandardCharsets.UTF_8);
    };

    public static AvpDecoder<Integer> IntegerDecoder =(byte[] buffer,int position,AvpHeader header)->{
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        int offset = (header.isVendorSpecific()?12:8) + position;
        // 1 skip header ..verify if vendor specific..then convert getdata and convert to String
        return ReadBytesUtils.readNBytesAsInt(buffer,offset,4);
    };
    
    public static GroupedAvpDecoder<Avp<?>>  GroupedAvpDecoder = (Avp<?> self,byte[] buffer, int position,AvpHeader header )->{
        //skip Group header and position to the "data" where nester AVP are present
        // All Avps are multiple of 4 octets...OctectString Avp, avpLen does not reflect padding!!
        int avpLen = header.getAvpLength()+header.getPaddingSize();
        int offset = position + (header.isVendorSpecific()?12:8);
        int index = position+offset;
        // loop until we reached the end of the Grouped AVP
        while (index < (position + avpLen)){
            AvpHeader localAvpheader = ReadAvpHeader.readAvpHeaderFromBytes(buffer, index);
            AvpIdToAvpMapper.AvpDefinition avpDefinition =AvpIdToAvpMapper.getAvpMapper()
                    .get(localAvpheader.getAvpCode());
            if(null != avpDefinition) {
                Avp<?> avp = avpDefinition.avpCreator.createInstance(localAvpheader,buffer,index);
                String callMethod = "set"+avp.getClass().getSimpleName();
                try {
                    Method method = self.getClass().getDeclaredMethod(callMethod,avp.getClass());
                    method.setAccessible(true);
                    method.invoke(self,avp);
                    method.setAccessible(false);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    System.out.println("AVP seems does not belong to this group... ignoring");
                    System.out.println(e);
                }
            }else {
                System.out.println("Unknown AVP ID");
            }
            // move to next position based on AVP len...and padding. All AVPS must be multiple of 4 octets
            index+= localAvpheader.getAvpLength()+ localAvpheader.getPaddingSize();
        }
        return self;
    };

}
