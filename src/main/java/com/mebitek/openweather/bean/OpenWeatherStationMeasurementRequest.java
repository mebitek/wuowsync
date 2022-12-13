package com.mebitek.openweather.bean;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class OpenWeatherStationMeasurementRequest implements Serializable {

 @JsonProperty(value = "station_id")
 private String stationId;
 private Long dt;
 private BigDecimal temperature;
 @JsonProperty(value = "wind_speed")
 private BigDecimal windSpeed;

 @JsonProperty(value = "wind_gust")
 private BigDecimal windGust;

 @JsonProperty(value = "wind_deg")
 private Integer windDeg;
 private BigDecimal pressure;
 private BigDecimal humidity;
 @JsonProperty(value = "rain_24h")
 private BigDecimal rain24h;

 @JsonProperty(value = "dew_point")
 private BigDecimal dewPoint;

 @JsonProperty(value = "heat_index")
 private BigDecimal heatIndex;

 public OpenWeatherStationMeasurementRequest() {
 }

 public String getStationId() {
  return stationId;
 }

 public void setStationId(String stationId) {
  this.stationId = stationId;
 }

 public Long getDt() {
  return dt;
 }

 public void setDt(Long dt) {
  this.dt = dt;
 }

 public BigDecimal getTemperature() {
  return temperature;
 }

 public void setTemperature(BigDecimal temperature) {
  this.temperature = temperature;
 }

 public BigDecimal getWindSpeed() {
  return windSpeed;
 }

 public void setWindSpeed(BigDecimal windSpeed) {
  this.windSpeed = windSpeed;
 }

 public BigDecimal getWindGust() {
  return windGust;
 }

 public void setWindGust(BigDecimal windGust) {
  this.windGust = windGust;
 }

 public Integer getWindDeg() {
  return windDeg;
 }

 public void setWindDeg(Integer windDeg) {
  this.windDeg = windDeg;
 }

 public BigDecimal getPressure() {
  return pressure;
 }

 public void setPressure(BigDecimal pressure) {
  this.pressure = pressure;
 }

 public BigDecimal getHumidity() {
  return humidity;
 }

 public void setHumidity(BigDecimal humidity) {
  this.humidity = humidity;
 }

 public BigDecimal getRain24h() {
  return rain24h;
 }

 public void setRain24h(BigDecimal rain24h) {
  this.rain24h = rain24h;
 }

 public BigDecimal getDewPoint() {
  return dewPoint;
 }

 public void setDewPoint(BigDecimal dewPoint) {
  this.dewPoint = dewPoint;
 }

 public BigDecimal getHeatIndex() {
  return heatIndex;
 }

 public void setHeatIndex(BigDecimal heatIndex) {
  this.heatIndex = heatIndex;
 }
}
