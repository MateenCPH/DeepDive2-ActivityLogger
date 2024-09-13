package dat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "city_info")

public class CityInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    //Properties class
    @Column(name= "building_code", nullable = false)
    private int buildingCode;
    @Column(name= "population", nullable = false)
    private Integer population;

    private double latitude;
    private double longitude;

    private String municipality;

    @OneToOne(mappedBy = "cityInfo", cascade = CascadeType.ALL)
    private Activity activity;


}
