package org.example.diameter.avp.common;

import lombok.Getter;
import org.example.diameter.avp.*;
import org.example.diameter.utils.EncodeAvp;

@AvpRegister(avpCode =458,avpBuilderMethod = "byteToAvp")
public class UserEquipmentInfo extends Avp<UserEquipmentInfo> {
    public static int avpCode = 458;
    public static byte flags = (byte) 0x40;
    @InnerAvp@Getter
    private UserEquipmentInfoType userEquipmentInfoType;
    @InnerAvp@Getter
    private UserEquipmentInfoValue userEquipmentInfoValue;

    private void setUserEquipmentInfoType(UserEquipmentInfoType userEquipmentInfoType) {
        this.userEquipmentInfoType = userEquipmentInfoType;
    }

    private void setUserEquipmentInfoValue(UserEquipmentInfoValue userEquipmentInfoValue) {
        this.userEquipmentInfoValue = userEquipmentInfoValue;
    }

    public UserEquipmentInfo(AvpHeader header, byte[] buffer, int position) {
        super(header, buffer, position);
    }

    public UserEquipmentInfo(UserEquipmentInfoType userEquipmentInfoType, UserEquipmentInfoValue userEquipmentInfoValue) {
        super(null);
        this.userEquipmentInfoType = userEquipmentInfoType;
        this.userEquipmentInfoValue = userEquipmentInfoValue;
    }

    public static AvpBuilder byteToAvp(){
        return new AvpBuilder((UserEquipmentInfo::new));
    }

    @Override
    public UserEquipmentInfo returnContent() {
        return this;
    }

    @Override
    public UserEquipmentInfo decode(byte[] buffer, int position, AvpHeader header) {
        return (UserEquipmentInfo) AvpTypeDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

    @Override
    public byte[] encode() {
        return EncodeAvp.encodeGroup(this,avpCode,flags,0);
    }

}
