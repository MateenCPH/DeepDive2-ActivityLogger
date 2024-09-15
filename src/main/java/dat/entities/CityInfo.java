package dat.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "city_info")

public class CityInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    //Properties class
    @Column(name= "building_code", nullable = false)
    private int buildingCode;
    @Column(name= "population", nullable = true)
    private Integer population;

    private double latitude;
    private double longitude;

    private String municipality;

    @OneToOne(mappedBy = "cityInfo", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Activity activity;


}
