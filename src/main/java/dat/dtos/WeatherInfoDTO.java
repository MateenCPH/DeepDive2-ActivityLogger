package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dat.entities.WeatherInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class WeatherInfoDTO {
    @JsonProperty("LocationName")
    private String locationName;
    @JsonProperty("CurrentData")
    private CurrentDataDTO currentData;

    public WeatherInfoDTO(WeatherInfo weatherInfo) {
        this.locationName = weatherInfo.getLocationName();
        this.currentData = new CurrentDataDTO(weatherInfo.getCurrentData());
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class CurrentDataDTO {
        private double temperature;
        private String skyText;
        private double humidity;
        private String windText;
    }
}
