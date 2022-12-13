package com.mebitek.ibm.bean;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class WundergroundCondition implements Serializable {

 private String stationID;
 private String obsTimeUtc;
 private String obsTimeLocal;
 private String neighborhood;
 private String country;
 private BigDecimal lon;
 private Long epoch;
 private BigDecimal lat;
 private Integer winddir;
 private BigDecimal humidity;
 private Integer qcStatus;

 @JsonProperty(value = "metric_si")
 private Metric metric;

 public String getStationID() {
  return stationID;
 }

 public void setStationID(String stationID) {
  this.stationID = stationID;
 }

 public String getObsTimeUtc() {
  return obsTimeUtc;
 }

 public void setObsTimeUtc(String obsTimeUtc) {
  this.obsTimeUtc = obsTimeUtc;
 }

 public String getObsTimeLocal() {
  return obsTimeLocal;
 }

 public void setObsTimeLocal(String obsTimeLocal) {
  this.obsTimeLocal = obsTimeLocal;
 }

 public String getNeighborhood() {
  return neighborhood;
 }

 public void setNeighborhood(String neighborhood) {
  this.neighborhood = neighborhood;
 }

 public String getCountry() {
  return country;
 }

 public void setCountry(String country) {
  this.country = country;
 }

 public BigDecimal getLon() {
  return lon;
 }

 public void setLon(BigDecimal lon) {
  this.lon = lon;
 }

 public Long getEpoch() {
  return epoch;
 }

 public void setEpoch(Long epoch) {
  this.epoch = epoch;
 }

 public BigDecimal getLat() {
  return lat;
 }

 public void setLat(BigDecimal lat) {
  this.lat = lat;
 }

 public Integer getWinddir() {
  return winddir;
 }

 public void setWinddir(Integer winddir) {
  this.winddir = winddir;
 }

 public BigDecimal getHumidity() {
  return humidity;
 }

 public void setHumidity(BigDecimal humidity) {
  this.humidity = humidity;
 }

 public Integer getQcStatus() {
  return qcStatus;
 }

 public void setQcStatus(Integer qcStatus) {
  this.qcStatus = qcStatus;
 }

 public Metric getMetric() {
  return metric;
 }

 public void setMetric(Metric metric) {
  this.metric = metric;
 }

 public static class Metric {

  private BigDecimal temp;
  private BigDecimal heatIndex;
  private BigDecimal dewpt;
  private BigDecimal windChill;
  private BigDecimal windSpeed;
  private BigDecimal windGust;
  private BigDecimal pressure;
  private BigDecimal precipRate;
  private BigDecimal precipTotal;
  private BigDecimal elev;

  public BigDecimal getTemp() {
   return temp;
  }

  public void setTemp(BigDecimal temp) {
   this.temp = temp;
  }

  public BigDecimal getHeatIndex() {
   return heatIndex;
  }

  public void setHeatIndex(BigDecimal heatIndex) {
   this.heatIndex = heatIndex;
  }

  public BigDecimal getDewpt() {
   return dewpt;
  }

  public void setDewpt(BigDecimal dewpt) {
   this.dewpt = dewpt;
  }

  public BigDecimal getWindChill() {
   return windChill;
  }

  public void setWindChill(BigDecimal windChill) {
   this.windChill = windChill;
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

  public BigDecimal getPressure() {
   return pressure;
  }

  public void setPressure(BigDecimal pressure) {
   this.pressure = pressure;
  }

  public BigDecimal getPrecipRate() {
   return precipRate;
  }

  public void setPrecipRate(BigDecimal precipRate) {
   this.precipRate = precipRate;
  }

  public BigDecimal getPrecipTotal() {
   return precipTotal;
  }

  public void setPrecipTotal(BigDecimal precipTotal) {
   this.precipTotal = precipTotal;
  }

  public BigDecimal getElev() {
   return elev;
  }

  public void setElev(BigDecimal elev) {
   this.elev = elev;
  }
 }







}
