package com.example.weatherapiapp;

public class WeatherReportModel {
    private int id;
    private String weatherStateName;
    private String weatherStateAbbr;
    private String windDirectionCompass;
    private String applicableDate;
    private float minTemp;
    private float maxTemp;
    private float theTemp;
    private float windSpeed;
    private float windDirection;
    private int airPressure;
    private int humidity;
    private float visibility;
    private int predictability;

    public WeatherReportModel(int id, String weatherStateName, String weatherStateAbbr, String windDirectionCompass, String created, String applicableDate,
                              float minTemp, float maxTemp, float theTemp, float windSpeed, float windDirection, int airPressure, int humidity, float visibility,
                              int predictability) {
        this.id = id;
        this.weatherStateName = weatherStateName;
        this.weatherStateAbbr = weatherStateAbbr;
        this.windDirectionCompass = windDirectionCompass;
        this.applicableDate = applicableDate;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.theTemp = theTemp;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.airPressure = airPressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictability = predictability;
    }

    public WeatherReportModel() {
    }

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                ", weatherStateName='" + weatherStateName +
                ", applicableDate='" + applicableDate +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", airPressure=" + airPressure +
                ", humidity=" + humidity +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }

    public void setWeatherStateAbbr(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }

    public void setWindDirectionCompass(String windDirectionCompass) {
        this.windDirectionCompass = windDirectionCompass;
    }

    public void setApplicableDate(String applicableDate) {
        this.applicableDate = applicableDate;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setTheTemp(float theTemp) {
        this.theTemp = theTemp;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(float windDirection) {
        this.windDirection = windDirection;
    }

    public void setAirPressure(int airPressure) {
        this.airPressure = airPressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public void setPredictability(int predictability) {
        this.predictability = predictability;
    }
}
