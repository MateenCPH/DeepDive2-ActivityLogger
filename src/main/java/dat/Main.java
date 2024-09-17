package dat;

import dat.config.HibernateConfig;
import dat.daos.ActivityDAO;
import dat.dtos.ActivityDTO;
import dat.dtos.CityInfoDTO;
import dat.dtos.WeatherInfoDTO;
import dat.entities.Activity;
import dat.entities.CityInfo;
import dat.entities.WeatherInfo;
import dat.services.ActivityService;
import dat.services.CityService;
import dat.services.WeatherService;
import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("activitylogger");
        ActivityDAO activityDAO = new ActivityDAO(emf);

// Record the start time
        long startTime = System.currentTimeMillis();

        // Persist an Activity with CityInfoDTO and WeatherInfoDTO
        /*String cityName = "Lyngby";  // Example city name

        try {
            //Build the CityInfoDTO
            CityInfoDTO cityInfoDTO = CityService.getCityInfo(cityName);
            //Build the CityInfo from the data provided by the CityInfoDTO
            CityInfo cityInfo = CityInfo.builder()
                    .cityName(cityInfoDTO.getName())
                    .buildingCode(cityInfoDTO.getProperties().getBuildingCode())
                    .population(cityInfoDTO.getProperties().getPopulation())
                    .latitude(cityInfoDTO.getVisualCenter().get(1))
                    .longitude(cityInfoDTO.getVisualCenter().get(0))
                    .municipality(cityInfoDTO.getMunicipalities().get(0).getName())
                    .build();

            //Build the WeatherInfoDTO
            WeatherInfoDTO weatherInfoDTO = WeatherService.getWeatherInfo(cityName);
            //Build the WeatherInfo from the data provided by the WeatherInfoDTO
            WeatherInfo weatherInfo = WeatherInfo.builder()
                    .locationName(weatherInfoDTO.getLocationName())
                    .temperature(weatherInfoDTO.getCurrentData().getTemperature())
                    .skyText(weatherInfoDTO.getCurrentData().getSkyText())
                    .windText(weatherInfoDTO.getCurrentData().getWindText())
                    .build();

            //Build the Activity with the CityInfo and WeatherInfo entities
            Activity activity = Activity.builder()
                    .exerciseDate(java.time.LocalDate.of(2024,9,14))
                    .exerciseType("Fitness")
                    .timeOfDay(java.time.LocalTime.now())
                    .duration(30.0)
                    .distance(5.0)
                    .comment("Morning workout " + cityName)
                    .cityInfo(cityInfo)
                    .weatherInfo(weatherInfo)
                    .build();

            Activity persistedActivity = activityDAO.create(activity);

            //System.out.println(activityDTO);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        //Get all activities and print them out
        Set<Activity> activities = activityDAO.getAll();
        activities.forEach(System.out::println);


        //Get all activities and convert them to JSON
        String jsonActivites = activityDAO.getAllAsJson();
        System.out.println(jsonActivites);

        //Get activity by date
        //Set<Activity> activityByDate = activityDAO.getActivityByDate(LocalDate.of(2024, 9, 14));
        //activityByDate.forEach(System.out::println);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Task runtime: " + duration + " milliseconds");
    }
}