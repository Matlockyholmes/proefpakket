package be.vdab.proefpakket.services;

import be.vdab.proefpakket.restclients.TemperatuurClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OmwServiceImpl implements OmwService {
    private final TemperatuurClient temperatuurClient;

    public OmwServiceImpl(TemperatuurClient temperatuurClient) {
        this.temperatuurClient = temperatuurClient;
    }

    @Override
    public BigDecimal geefTemperatuur(String plaats) {
        return temperatuurClient.getTemperatuur(plaats);
    }
}
