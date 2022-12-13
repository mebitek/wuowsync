package com.mebitek.openweather.bean;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;


public class OpenWeatherStation implements Serializable {
 private String id;
 @JsonProperty(value = "created_at")
 private String createdAt;

 @JsonProperty(value = "updated_at")
 private String updatedAt;

 @JsonProperty(value = "external_id")
 private String externalId;
 private String name;
 private BigDecimal longitude;
 private BigDecimal latitude;
 private BigDecimal altitude;
 private Integer rank;

 public OpenWeatherStation() {
 }

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public String getCreatedAt() {
  return createdAt;
 }

 public void setCreatedAt(String createdAt) {
  this.createdAt = createdAt;
 }

 public String getUpdatedAt() {
  return updatedAt;
 }

 public void setUpdatedAt(String updatedAt) {
  this.updatedAt = updatedAt;
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

 public BigDecimal getLongitude() {
  return longitude;
 }

 public void setLongitude(BigDecimal longitude) {
  this.longitude = longitude;
 }

 public BigDecimal getLatitude() {
  return latitude;
 }

 public void setLatitude(BigDecimal latitude) {
  this.latitude = latitude;
 }

 public BigDecimal getAltitude() {
  return altitude;
 }

 public void setAltitude(BigDecimal altitude) {
  this.altitude = altitude;
 }

 public Integer getRank() {
  return rank;
 }

 public void setRank(Integer rank) {
  this.rank = rank;
 }
}
