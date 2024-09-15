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

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("activitylogger");
        ActivityDAO activityDAO = new ActivityDAO(emf);

// Record the start time
        long startTime = System.currentTimeMillis();
        String cityName = "Roskilde";  // Example city name

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
                    .exerciseDate(java.time.LocalDate.now())
                    .exerciseType("Running")
                    .timeOfDay(java.time.LocalTime.now())
                    .duration(30.0)
                    .distance(5.0)
                    .comment("Morning run " + cityName)
                    .cityInfo(cityInfo)
                    .weatherInfo(weatherInfo)
                    .build();

            Activity persistedActivity = activityDAO.create(activity);

            //System.out.println(activityDTO);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*  This is the code that was in the main method before the refactoring
        if (cityInfoArray != null && cityInfoArray.length > 0) {
            CityInfoDTO cityInfo = cityInfoArray[0];  // Get the first city info result

            System.out.println("City ID: " + cityInfo.getId());
            System.out.println("City Name: " + cityInfo.getName());

            // Print Bounding Box
            if (cityInfo.getBbox() != null && cityInfo.getBbox().size() == 4) {
                System.out.println("Bounding Box: " +
                        "xmin = " + cityInfo.getBbox().get(0) +
                        ", ymin = " + cityInfo.getBbox().get(1) +
                        ", xmax = " + cityInfo.getBbox().get(2) +
                        ", ymax = " + cityInfo.getBbox().get(3));
            }
            // Print Municipality Info
            if (cityInfo.getKommuner() != null && !cityInfo.getKommuner().isEmpty()) {
                KommuneDTO kommuneDTO = cityInfo.getKommuner().get(0);  // Get the first municipality
                System.out.println("Municipality Code: " + kommuneDTO.getCode());
                System.out.println("Municipality Name: " + kommuneDTO.getName());
            }
        } else {
            System.out.println("No city information found.");
        }
        System.out.println("Last updated: " + cityInfoArray[0].getLastUpdated());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Task runtime: " + duration + " milliseconds");

        WeatherInfoDTO weatherInfo = WeatherService.getWeatherInfo("Roskilde");

        if (weatherInfo != null) {
            System.out.println("Location: " + weatherInfo.getLocationName());

            CurrentDataDTO currentData = weatherInfo.getCurrentData();
            System.out.println("Temperature: " + currentData.getTemperature() + "°C");
            System.out.println("Sky: " + currentData.getSkyText());
            System.out.println("Wind: " + currentData.getWindText());
        } else {
            System.out.println("No weather information found.");
        }
    }*/
    }

    /*StringBuilder sb = new StringBuilder();
            sb.append("City Name: ").append(cityInfo.getName()).append(System.lineSeparator());
            sb.append("Municipality Name: ").append(cityInfo.getKommuner().get(0).getName()).append(" kommune").append(System.lineSeparator());
            sb.append("Bounding Box: xmin = ").append(cityInfo.getBbox().get(0))
                    .append(", ymin = ").append(cityInfo.getBbox().get(1))
                    .append(", xmax = ").append(cityInfo.getBbox().get(2))
                    .append(", ymax = ").append(cityInfo.getBbox().get(3));*/
}