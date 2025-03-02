package org.example.diameter.avp.common;

import lombok.Getter;
import org.example.diameter.avp.Avp;
import org.example.diameter.avp.AvpDecoders;
import org.example.diameter.avp.AvpHeader;

public class UserEquipmentInfo extends Avp<UserEquipmentInfo> {
    public static int avpCode = 458;
    public static byte flags = (byte) 0x40;
    @Getter
    private UserEquipmentInfoType userEquipmentInfoType;
    @Getter
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

    @Override
    public UserEquipmentInfo decode(byte[] buffer, int position, AvpHeader header) {
        return (UserEquipmentInfo) AvpDecoders.GroupedAvpDecoder.decode(this, buffer, position, header);
    }

}
