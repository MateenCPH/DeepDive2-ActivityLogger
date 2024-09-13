package dat.entities;

import dat.dtos.CityInfoDTO;
import dat.dtos.WeatherInfoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "activity")

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
