package be.vdab.proefpakket.restclients;

import be.vdab.proefpakket.exceptions.TemperatuurNietGevondenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


@Component
public class OmwTemperatuurClient implements TemperatuurClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String omwURL;
    private RestTemplate restTemplate;

    public OmwTemperatuurClient(@Value("${openWeatherMapURL}")String omwURL, RestTemplateBuilder restTemplateBuilder) {
        this.omwURL = omwURL;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public BigDecimal getTemperatuur(String plaats) {
        try{
            Weer weer = restTemplate.getForObject(omwURL, Weer.class, plaats);
            return weer.getMain().getTemp();
        }catch (RestClientException ex){
            logger.error("Kan temperatuur niet lezen", ex);
            throw new TemperatuurNietGevondenException();
        }
    }
}
