package org.example.diameter.avp.types;


import org.example.diameter.utils.ReadBytesUtils;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HexFormat;

class TimeTest {

    @Test
    public void testTime(){
        //3951219578	03/17/2025, 5:24:59 PM
        byte[] bytes = HexFormat.of().parseHex("EB82D37A");
        Time time = new Time(bytes);

        System.out.println(time.getTime());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond());
    }

    @Test
    public void ntpFromNowPlusNseconds(){

        Time time = Time.ofNSecondsFromNow(3600);
        byte[] bytes = time.getValue();
        long value = ReadBytesUtils.readNBytesAsLong(bytes,0,4);
        System.out.println(value);
        System.out.println(new Date());
        System.out.println(time.getTime());

        long X = LocalDateTime.of(1900,1,1,0,0).toInstant(ZoneOffset.UTC).getEpochSecond();
        System.out.println(X);

    }

}