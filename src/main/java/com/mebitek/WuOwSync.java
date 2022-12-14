package com.mebitek;

import com.mebitek.ibm.WundergroundService;
import com.mebitek.ibm.bean.WundergroundCondition;
import com.mebitek.openweather.OpenWeatherService;
import com.mebitek.openweather.bean.OpenWeatherStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WuOwSync {

 private static Logger LOGGER = LoggerFactory.getLogger(WuOwSync.class);
 public static void main(String[] args) {
  LOGGER.info("starting Weather Underground to Open Weather Sync");

  if (args.length!=3) {
   LOGGER.error("usage: WuOwSync stationId apiKey appId");
   return;
  }

  String stationId = args[0];
  String apiKey = args[1];
  String appId = args[2];

  WundergroundService wundergroundService = new WundergroundService(stationId,apiKey);

  WundergroundCondition actualCondition = wundergroundService.getActualCondition();

  OpenWeatherService openWeatherService = new OpenWeatherService(stationId, appId);

  OpenWeatherStation weatherStation = openWeatherService.getWeatherStation();
  String openWeatherStationId;
  if (weatherStation!=null) {
   openWeatherStationId = weatherStation.getId();
  } else {
   // register station
   openWeatherStationId = openWeatherService.registerStation(actualCondition);
  }

  openWeatherService.sendMeasurement(openWeatherStationId, actualCondition);
 }
}
