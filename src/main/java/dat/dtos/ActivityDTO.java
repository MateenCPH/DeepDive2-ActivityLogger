package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class ActivityDTO {
    private LocalDate exerciseDate;
    private String exerciseType;
    private String timeOfDay;
    private double duration;
    private double distance;
    private String comment;
    private WeatherInfoDTO weatherInfoDTO;
    private CityInfoDTO cityInfoDTO;

}
