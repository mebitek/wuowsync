import com.mebitek.ibm.WundergroundService;
import com.mebitek.ibm.bean.WundergroundCondition;
import com.mebitek.openweather.OpenWeatherService;
import com.mebitek.openweather.bean.OpenWeatherStation;

public class WuOwSync {
 public static void main(String[] args) {
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
