package be.vdab.proefpakket.restclients;

import java.math.BigDecimal;

public interface TemperatuurClient {
    BigDecimal getTemperatuur(String plaats);
}
