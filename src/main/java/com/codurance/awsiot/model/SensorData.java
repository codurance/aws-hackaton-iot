package com.codurance.awsiot.model;

import java.io.Serializable;

public class IoTData implements Serializable {

//    private String uuid;
    private String date;
    private String temperature;
    private String txt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
