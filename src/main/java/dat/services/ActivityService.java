package dat.services;

import dat.dtos.ActivityDTO;
import dat.dtos.CityInfoDTO;
import dat.dtos.WeatherInfoDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityService {

    //This method will be used to create a new ActivityDTO, and enrich it with data from the CityService and WeatherService
    public static ActivityDTO createActivityWithAttributes(String cityName, LocalDate exerciseDate, String exerciseType, LocalTime timeOfDay, double duration, double distance, String comment) {

        // Enrich with WeatherInfo
        WeatherInfoDTO weatherInfo = WeatherService.getWeatherInfo(cityName);

        // Enrich with CityInfo
        CityInfoDTO cityInfoDTO = null;
        try {
            cityInfoDTO = CityService.getCityInfo(cityName);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ActivityDTO(exerciseDate, exerciseType, timeOfDay, duration, distance, comment,cityInfoDTO, weatherInfo);  // Return the enriched ActivityDTO
    }

    public static ActivityDTO createActivityWithEntities(String cityName, CityInfoDTO cityInfoDTO, WeatherInfoDTO weatherInfoDTO) {
        return new ActivityDTO(LocalDate.now(), "Running", LocalTime.now(), 30.0, 5.0, "Good run", cityInfoDTO, weatherInfoDTO);
    }
}



