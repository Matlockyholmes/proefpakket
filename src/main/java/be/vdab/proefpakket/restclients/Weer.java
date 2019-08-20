package be.vdab.proefpakket.restclients;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Weer {
    private Main main;

    public Main getMain() {
        return main;
    }
}
