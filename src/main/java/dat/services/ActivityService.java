package dat.services;

import dat.dtos.ActivityDTO;
import dat.dtos.CityInfoDTO;
import dat.dtos.WeatherInfoDTO;

import java.io.IOException;
import java.time.LocalDate;

public class ActivityService {

    //This method will be used to create a new ActivityDTO, and enrich it with data from the CityService and WeatherService
    public static ActivityDTO createActivity(String cityName, String exerciseType, LocalDate exerciseDate, double duration, double distance, String comment) {

        // Create the base ActivityDTO
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setExerciseType(exerciseType);
        activityDTO.setExerciseDate(exerciseDate);
        activityDTO.setDuration(duration);
        activityDTO.setDistance(distance);
        activityDTO.setComment(comment);

        // Enrich with CityInfo
        CityInfoDTO cityInfoDTO = null;
        try {
            cityInfoDTO = CityService.getCityInfo(cityName);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (cityInfoDTO != null) {
            activityDTO.setCityInfoDTO(cityInfoDTO);  // Get the first result for the city
        }

        // Enrich with WeatherInfo
        WeatherInfoDTO weatherInfo = WeatherService.getWeatherInfo(cityName);
        if (weatherInfo != null) {
            activityDTO.setWeatherInfoDTO(weatherInfo);
        }

        return activityDTO;  // Return the enriched ActivityDTO
    }
}



