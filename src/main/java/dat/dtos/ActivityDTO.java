package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class ActivityDTO {
    private LocalDate exerciseDate;
    private String exerciseType;
    private LocalTime timeOfDay;
    private double duration;
    private double distance;
    private String comment;
    private CityInfoDTO cityInfoDTO;
    private WeatherInfoDTO weatherInfoDTO;

}
