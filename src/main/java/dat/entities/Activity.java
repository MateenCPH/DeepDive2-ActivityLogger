package dat.entities;

import dat.dtos.CityInfoDTO;
import dat.dtos.WeatherInfoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "activity")
@NamedQuery(name = "Activity.findByDate", query = "SELECT a FROM Activity a WHERE a.exerciseDate = :exerciseDate")
@NamedQuery(name = "Activity.getAll", query = "SELECT a FROM Activity a")

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "exercise_date", nullable = false)
    private LocalDate exerciseDate;

    @Column(name = "exercise_type", nullable = false)
    private String exerciseType;

    @Column(name = "time_of_day", nullable = false)
    private LocalTime timeOfDay;
    private double duration;
    private double distance;
    private String comment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_info_id")
    private CityInfo cityInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_info_id")
    private WeatherInfo weatherInfo;

    //PrePersist and PreUpdate methods
    @Column(name = "created_date_time", nullable = false, updatable = false)
    private LocalDateTime createdDateTime;
    @ToString.Exclude
    @Column(name = "updated_date_time", nullable = false)
    private LocalDateTime updatedDateTime;
    @PrePersist
    public void prePersist() {
        if (createdDateTime == null) {
            createdDateTime = LocalDateTime.now();
        }
        if (updatedDateTime == null) {
            updatedDateTime = LocalDateTime.now();
        }
    }
    @PreUpdate
    public void preUpdate() {
        updatedDateTime = LocalDateTime.now();
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity activity)) return false;
        return Double.compare(duration, activity.duration) == 0 && Double.compare(distance, activity.distance) == 0 && Objects.equals(id, activity.id) && Objects.equals(exerciseDate, activity.exerciseDate) && Objects.equals(exerciseType, activity.exerciseType) && Objects.equals(timeOfDay, activity.timeOfDay) && Objects.equals(comment, activity.comment) && Objects.equals(cityInfo, activity.cityInfo) && Objects.equals(weatherInfo, activity.weatherInfo) && Objects.equals(createdDateTime, activity.createdDateTime) && Objects.equals(updatedDateTime, activity.updatedDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exerciseDate, exerciseType, timeOfDay, duration, distance, comment, cityInfo, weatherInfo, createdDateTime, updatedDateTime);
    }*/
}
