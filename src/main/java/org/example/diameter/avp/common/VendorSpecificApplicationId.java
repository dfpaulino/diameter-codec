package org.example.diameter.avp.common;


import org.example.diameter.Utils.ReadAvpHeader;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpHeader;
import org.example.diameter.avp.AvpIdToAvpMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VendorSpecificApplicationId extends Avp<VendorSpecificApplicationId> {

    private VendorId vendorId;
    private AuthApplicationId authApplicationId;

    public VendorSpecificApplicationId(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public void setVendorId(VendorId vendorId) {
        this.vendorId = vendorId;
    }
    
    public void setAuthApplicationId(AuthApplicationId authApplicationId) {
        this.authApplicationId = authApplicationId;
    }

    @Override
    public VendorSpecificApplicationId decode(byte[] buffer, int position, AvpHeader header) {

        //skip Group header and position to the "data" where nester AVP are present
        // All Avps are multiple of 4 octets...OctectString Avp, avpLen does not reflect padding!!
        int avpLen = this.getHeader().getAvpLength()+this.getHeader().getAvpLength()%4;
        int offset = position + (this.getHeader().isVendorSpecific()?12:8);
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
                    Method method = this.getClass().getMethod(callMethod,avp.getClass());
                    method.invoke(this,avp);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    System.out.println("AVP seems does not belong to this group... ignoring");
                    System.out.println(e);
                }
            }else {
                System.out.println("Unknown AVP ID");
            }
            // move to next position based on AVP len...and padding. All AVPS must be multiple of 4 octets
            index+= header.getAvpLength()+(header.getAvpLength()%4);
        }
        return this;
    }
}
