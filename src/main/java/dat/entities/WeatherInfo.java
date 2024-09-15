package dat.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "weather_info")

public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Column(nullable = false)
    private double temperature;

    @Column(name = "sky_text", nullable = false)
    private String skyText;

     @Column(nullable = false)
    private double humidity;

     @Column(name = "wind_text", nullable = false)
    private String windText;

     @OneToOne(mappedBy = "weatherInfo", cascade = CascadeType.ALL)
     @ToString.Exclude
    private Activity activity;
}
