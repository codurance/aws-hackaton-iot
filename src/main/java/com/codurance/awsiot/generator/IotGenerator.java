package com.codurance.awsiot.generator;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IotGenerator {

    @Scheduled(fixedDelay = 5000)
    public void generate(){
        getClass();
    }
}
