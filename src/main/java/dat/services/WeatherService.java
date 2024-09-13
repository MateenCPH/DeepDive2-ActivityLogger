package dat.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dat.dtos.WeatherInfoDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService<T> {

    public static <T> T getWeatherInfo(Class<T> dtoClass, String location) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Create an HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Create a request
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .header("Accept", "application/json")
                    .uri(new URI("https://vejr.eu/api.php?location=" + location + "&degree=C"))
                    .GET()
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the status code and return the DTO if successful
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), dtoClass);  // Deserialize JSON into WeatherInfoDTO
            } else {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
