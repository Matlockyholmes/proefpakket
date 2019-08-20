package be.vdab.proefpakket.restclients;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Main {
    @JsonProperty("temp")
    private BigDecimal temp;

    public BigDecimal getTemp() {
        return temp;
    }
}
