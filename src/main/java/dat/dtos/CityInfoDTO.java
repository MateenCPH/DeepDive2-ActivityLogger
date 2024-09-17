package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import dat.entities.CityInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Properties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class CityInfoDTO {
    @JsonSetter("primærtnavn")
    private String name;
    @JsonSetter("egenskaber")
    private Properties properties;
    @JsonSetter("visueltcenter")
    private List<Double> visualCenter;
    @JsonSetter("kommuner")
    private List<KommuneDTO> municipalities;

    public CityInfoDTO(CityInfo cityInfo) {
        this.name = cityInfo.getCityName();
        this.properties = new Properties();
        this.properties.setBuildingCode(cityInfo.getBuildingCode());
        this.properties.setPopulation(cityInfo.getPopulation());
        this.visualCenter = List.of(cityInfo.getLatitude(), cityInfo.getLongitude());
        this.municipalities = List.of(new KommuneDTO(cityInfo.getMunicipality(), ""));
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KommuneDTO {
        @JsonProperty("navn")
        private String name;
        @JsonProperty("kode")
        private String code;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public class Properties {
        @JsonSetter("bebyggelseskode")
        private int buildingCode;
        @JsonSetter("indbyggerantal")
        private Integer population;
    }
}
