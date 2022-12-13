package com.mebitek.openweather.bean;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class OpenWeatherStationRegisterRequest implements Serializable {


 @JsonProperty("external_id")
 private String externalId;
 private String name;
 private BigDecimal latitude;
 private BigDecimal longitude;
 private BigDecimal altitude;

 public OpenWeatherStationRegisterRequest() {
 }

 public String getExternalId() {
  return externalId;
 }

 public void setExternalId(String externalId) {
  this.externalId = externalId;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public BigDecimal getLatitude() {
  return latitude;
 }

 public void setLatitude(BigDecimal latitude) {
  this.latitude = latitude;
 }

 public BigDecimal getLongitude() {
  return longitude;
 }

 public void setLongitude(BigDecimal longitude) {
  this.longitude = longitude;
 }

 public BigDecimal getAltitude() {
  return altitude;
 }

 public void setAltitude(BigDecimal altitude) {
  this.altitude = altitude;
 }
}
