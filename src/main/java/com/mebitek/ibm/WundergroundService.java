package com.mebitek.ibm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mebitek.ibm.bean.WundergroundCondition;
import com.mebitek.ibm.bean.WundergroundObservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class WundergroundService {

 private final String stationId;
 private final String apiKey;

 private final RestTemplate restTemplate;

 private static final String apiUrl = "https://api.weather.com/v2/pws/observations/current";

 public WundergroundService(String stationId, String apiKey) {
  this.stationId = stationId;
  this.apiKey = apiKey;
  this.restTemplate = new RestTemplate();
  MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
  converter.setObjectMapper(new ObjectMapper());
  restTemplate.getMessageConverters().add(converter);
 }

 public WundergroundCondition getActualCondition() {

  String url = buildApiUrl();

  ResponseEntity<WundergroundObservation> responseEntity = restTemplate
          .getForEntity(url, WundergroundObservation.class);

  if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
   throw new RuntimeException("Cannot get weather underground actual condition");
  }

  if (responseEntity.getBody() == null) {
   throw new RuntimeException("Cannot get weather underground actual condition");
  }

  WundergroundObservation observation = responseEntity.getBody();
  if (observation.getObservations().size() > 0) {
   return observation.getObservations().get(0);
  } else {
   return null;
  }

 }

 private String buildApiUrl() {

  return apiUrl +
          "?stationId=" +
          this.stationId +
          "&format=json&units=s&numericPrecision=decimal&apiKey=" +
          this.apiKey;
 }

}
