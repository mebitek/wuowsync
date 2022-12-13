package com.mebitek.openweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mebitek.ibm.bean.WundergroundCondition;
import com.mebitek.openweather.bean.OpenWeatherStation;
import com.mebitek.openweather.bean.OpenWeatherStationMeasurementRequest;
import com.mebitek.openweather.bean.OpenWeatherStationRegisterRequest;
import com.mebitek.openweather.bean.OpenWeatherStationRegisterResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OpenWeatherService {

 private final String appId;

 private final String externalStationId;

 private final String stationApiUrl = "https://api.openweathermap.org/data/3.0/stations";
 private final String measurementApiUrl = "https://api.openweathermap.org/data/3.0/measurements";

 private final RestTemplate restTemplate;

 public OpenWeatherService(String externalStationId, String appId) {
  this.appId = appId;
  this.externalStationId = externalStationId;
  this.restTemplate = new RestTemplate();
  MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
  converter.setObjectMapper(new ObjectMapper());
  restTemplate.getMessageConverters().add(converter);
 }

 public OpenWeatherStation getWeatherStation() {
  String url = buildApiUrl(stationApiUrl);
  ResponseEntity<List<OpenWeatherStation>> exchange = restTemplate
          .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<OpenWeatherStation>>() {
                  }
          );
  if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
   throw new RuntimeException("Cannot get open weather station");
  }
  if (exchange.getBody() == null) {
   throw new RuntimeException("Cannot get open weather station");
  }
  List<OpenWeatherStation> stationList = exchange.getBody();
  return stationList.stream().filter(openWeatherStation -> openWeatherStation.getExternalId().equals(this.externalStationId)).findFirst().orElse(null);
 }

 public String registerStation(WundergroundCondition wundergroundCondition) {

  OpenWeatherStationRegisterRequest request = new OpenWeatherStationRegisterRequest();

  request.setExternalId(wundergroundCondition.getStationID());
  request.setName(wundergroundCondition.getNeighborhood());
  request.setLatitude(wundergroundCondition.getLat());
  request.setLongitude(wundergroundCondition.getLon());
  request.setAltitude(wundergroundCondition.getMetric().getElev());

  String url = buildApiUrl(this.stationApiUrl);

  ResponseEntity<OpenWeatherStationRegisterResponse> responseEntity = restTemplate.postForEntity(url, request, OpenWeatherStationRegisterResponse.class);
  if (!responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
   throw new RuntimeException("Cannot create open weather station");
  }

  if (responseEntity.getBody() == null) {
   throw new RuntimeException("Cannot create open weather station");
  }

  return responseEntity.getBody().getId();
 }

 public void sendMeasurement(String stationId, WundergroundCondition condition) {
  OpenWeatherStationMeasurementRequest request = new OpenWeatherStationMeasurementRequest();
  request.setStationId(stationId);

  Date obsTimeUtc;
  try {
   obsTimeUtc = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'").parse(condition.getObsTimeUtc());
  } catch (ParseException e) {
   throw new RuntimeException(e);
  }

  long time = obsTimeUtc.getTime() / 1000;
  request.setDt(time);
  request.setTemperature(condition.getMetric().getTemp());
  request.setTemperature(condition.getMetric().getTemp());
  request.setWindSpeed(condition.getMetric().getWindSpeed());
  request.setWindGust(condition.getMetric().getWindGust());
  request.setWindDeg(condition.getWinddir());
  request.setPressure(condition.getMetric().getPressure());
  request.setHumidity(condition.getHumidity());
  request.setRain24h(condition.getMetric().getPrecipTotal());
  request.setDewPoint(condition.getMetric().getDewpt());
  request.setHeatIndex(condition.getMetric().getHeatIndex());

  String url = buildApiUrl(this.measurementApiUrl);

  ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, Collections.singletonList(request), String.class);
  if (!responseEntity.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
   throw new RuntimeException("Cannot send open weather measurement");
  }
 }

 private String buildApiUrl(String url) {

  return url +
          "?appid=" +
          this.appId;
 }
}
