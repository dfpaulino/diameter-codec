package org.example.diameter.avp.types;

import org.example.diameter.utils.ReadBytesUtils;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Time {
    private static final long SEC_1900_1970 = 2208988800L;
    private byte[] value = new byte[4];

    public Time(byte[] value) {
        this.value = value;
    }

    public OffsetDateTime getTime(){
        long seconds_since_1900 = ReadBytesUtils.readNBytesAsLong(value,0,4);
        long seconds_since_1970 = seconds_since_1900 - SEC_1900_1970;
        OffsetDateTime odt = OffsetDateTime.ofInstant(Instant.ofEpochSecond(seconds_since_1970),ZoneId.systemDefault());
        return odt;
    }

    public byte[] getValue() {
        return value;
    }

    public static Time ofNSecondsFromNow(long seconds) {
        return new Time(fromNowPlusSecondsToNtp(seconds));
    }


    public static byte[] fromNowPlusSecondsToNtp(long seconds){

        long ntp_sec = new Date().toInstant().getEpochSecond()+seconds + SEC_1900_1970;
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((ntp_sec>>24)&0xff);
        bytes[1] = (byte)((ntp_sec>>16)&0xff);
        bytes[2] = (byte)((ntp_sec>>8)&0xff);
        bytes[3] = (byte)((ntp_sec)&0xff);
        return bytes;
    }

}
