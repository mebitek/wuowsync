package com.mebitek.openweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mebitek.ibm.bean.WundergroundCondition;
import com.mebitek.openweather.bean.OpenWeatherStation;
import com.mebitek.openweather.bean.OpenWeatherStationMeasurementRequest;
import com.mebitek.openweather.bean.OpenWeatherStationRegisterRequest;
import com.mebitek.openweather.bean.OpenWeatherStationRegisterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OpenWeatherService {

 private static Logger LOGGER = LoggerFactory.getLogger(OpenWeatherService.class);

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
  LOGGER.info("getting Open Weather stations");

  String url = buildApiUrl(stationApiUrl);
  ResponseEntity<List<OpenWeatherStation>> responseEntity = restTemplate
          .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<OpenWeatherStation>>() {
                  }
          );
  if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
   throw new RuntimeException("Cannot get open weather station");
  }
  if (responseEntity.getBody() == null) {
   throw new RuntimeException("Cannot get open weather station");
  }

  LOGGER.info("getting Open Weather stations: status code is {}", responseEntity.getStatusCode());
  List<OpenWeatherStation> stationList = responseEntity.getBody();
  return stationList.stream().filter(openWeatherStation -> openWeatherStation.getExternalId().equals(this.externalStationId)).findFirst().orElse(null);
 }

 public String registerStation(WundergroundCondition wundergroundCondition) {
  LOGGER.info("register Open Weather station: {}", wundergroundCondition.getStationID());


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
  LOGGER.info("register Open Weather stations: status code is {}", responseEntity.getStatusCode());


  return responseEntity.getBody().getId();
 }

 public void sendMeasurement(String stationId, WundergroundCondition condition) {
  LOGGER.info("send measurement Open Weather to station: {}", stationId);

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
  LOGGER.info("send measurement Open Weather to station: status code is {}", responseEntity.getStatusCode());

 }

 private String buildApiUrl(String url) {

  return url +
          "?appid=" +
          this.appId;
 }
}
