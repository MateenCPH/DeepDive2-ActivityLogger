package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dat.entities.Activity;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    //Constructor to map from Activity entity to ActivityDTO
    public ActivityDTO(Activity activity) {
        this.exerciseDate = activity.getExerciseDate();
        this.exerciseType = activity.getExerciseType();
        this.timeOfDay = activity.getTimeOfDay();
        this.duration = activity.getDuration();
        this.distance = activity.getDistance();
        this.comment = activity.getComment();
        this.cityInfoDTO = new CityInfoDTO(activity.getCityInfo());
        this.weatherInfoDTO = new WeatherInfoDTO(activity.getWeatherInfo());
    }
}
