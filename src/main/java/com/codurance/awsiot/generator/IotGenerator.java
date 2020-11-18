package com.codurance.awsiot.generator;

import com.codurance.awsiot.model.IoTData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
@Component
public class IotGenerator {
    enum Origin {
        Bedroom,
        Kitchen,
        LivingRoom
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    private static final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Scheduled(fixedDelay = 5000)
    public void generate() throws JsonProcessingException {
        IoTData data = new IoTData();
        data.setDate(generateDate());
        data.setTemperature(generateTemperature());
        data.setTxt(generateText());
        String json = ow.writeValueAsString(data);
    }


    private String generateText() {
        Random origin = new Random();
         int originResult = origin.nextInt(3);
         switch (originResult) {
             case 0:
                 return Origin.Bedroom.toString();
             case 1:
                 return Origin.Kitchen.toString();
             case 2:
                 return Origin.LivingRoom.toString();
             default: throw new UnsupportedOperationException();
         }
    }


    private String generateTemperature(){
        StringBuilder temperatureBuilder = new StringBuilder();

        Random celciusOrFarenheitRandom = new Random();
        int celciusOrFarenheitResult = celciusOrFarenheitRandom.nextInt(3);

        if(celciusOrFarenheitResult == 1){
            Random celciusRandom = new Random();
            int addition = celciusRandom.nextInt(81);

            temperatureBuilder.append(-30 + addition);
            temperatureBuilder.append("C");
        }else{
            Random farengheitRandom = new Random();
            int addition = farengheitRandom.nextInt(101);

            temperatureBuilder.append(-22 + addition);
            temperatureBuilder.append("F");
        }

        return temperatureBuilder.toString();
    }

    private String generateDate() {

        LocalDateTime localNow = LocalDateTime.now();

        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));

        Random random = new Random();
        int option = random.nextInt(100) % 3;
        String difference = "+0";

        switch (option){
            case 0: break;

            case 1: {
                zonedUTC = zonedUTC.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
                difference = "+9";
            }break;
            case 2: {
                zonedUTC = zonedUTC.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
                difference = "-8";
            }break;

            default: throw new UnsupportedOperationException();
        }

        String formattedDateTime = zonedUTC.format(formatter).concat(difference);

        return formattedDateTime;
    }
}
