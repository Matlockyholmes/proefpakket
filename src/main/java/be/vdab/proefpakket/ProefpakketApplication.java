package be.vdab.proefpakket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class ProefpakketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProefpakketApplication.class, args);
    }

}
