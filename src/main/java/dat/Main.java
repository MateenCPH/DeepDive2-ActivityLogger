package dat;

import dat.config.HibernateConfig;
import dat.daos.PersonDAO;
import dat.dtos.CityInfoDTO;
import dat.dtos.KommuneDTO;
import dat.dtos.WeatherInfoDTO;
import dat.entities.Person;
import dat.services.CityService;
import dat.services.WeatherService;
import jakarta.persistence.EntityManagerFactory;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
// Record the start time
        long startTime = System.currentTimeMillis();
        String cityName = "Aarhus";  // Example city name

        CityInfoDTO[] cityInfoArray = CityService.getCityInfo(CityInfoDTO[].class, cityName);
        System.out.println(Arrays.toString(cityInfoArray));

        WeatherInfoDTO weatherInfoDTO = WeatherService.getWeatherInfo(WeatherInfoDTO.class, cityName);
        System.out.println(weatherInfoDTO);





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