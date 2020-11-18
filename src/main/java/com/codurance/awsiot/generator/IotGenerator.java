package com.codurance.awsiot.generator;

import com.codurance.awsiot.model.IoTData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class IotGenerator {

    @Scheduled(fixedDelay = 5000)
    public void generate() {
        IoTData data = new IoTData();
        data.setDate();
        data.setTemperature();
        data.setTxt();
    }
    private String generateDate() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime localNow = LocalDateTime.now();
        // setting UTC as the timezone
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        // converting to IST
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime zonedPST = zonedUTC.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));

        String localDateTime = LocalDateTime.now().format(formatter);
        return
    }
}
