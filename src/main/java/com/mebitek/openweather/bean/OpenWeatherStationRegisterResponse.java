package com.mebitek.openweather.bean;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class OpenWeatherStationRegisterResponse implements Serializable {

 @JsonProperty(value = "ID")
 private String id;
 @JsonProperty(value = "created_at")
 private String createdAt;

 @JsonProperty(value = "updated_at")
 private String updatedAt;

 @JsonProperty(value = "user_id")
 private String userId;

 @JsonProperty(value = "external_id")
 private String externalId;
 private String name;
 private BigDecimal latitude;
 private BigDecimal longitude;
 private BigDecimal altitude;
 @JsonProperty("source_type")
 private Integer sourceType;

 public OpenWeatherStationRegisterResponse() {
 }

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public String getUpdatedAt() {
  return updatedAt;
 }

 public void setUpdatedAt(String updatedAt) {
  this.updatedAt = updatedAt;
 }

 public String getCreatedAt() {
  return createdAt;
 }

 public void setCreatedAt(String createdAt) {
  this.createdAt = createdAt;
 }

 public String getUserId() {
  return userId;
 }

 public void setUserId(String userId) {
  this.userId = userId;
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

 public Integer getSourceType() {
  return sourceType;
 }

 public void setSourceType(Integer sourceType) {
  this.sourceType = sourceType;
 }
}
