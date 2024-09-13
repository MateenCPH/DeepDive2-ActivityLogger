package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class CityInfoDTO {
    private String id;
    @JsonProperty("primærtnavn")
    private String name;
    private List<Double> bbox;
    private List<KommuneDTO> kommuner;
    @JsonProperty("ændret")
    private String lastUpdated;
}
