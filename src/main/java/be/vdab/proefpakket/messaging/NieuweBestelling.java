package be.vdab.proefpakket.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NieuweBestelling {
    private String emailadres;
    private String brouwerNaam;

    protected NieuweBestelling() {
    }

    public NieuweBestelling(String emailadres, String brouwerNaam) {
        this.emailadres = emailadres;
        this.brouwerNaam = brouwerNaam;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public String getBrouwerNaam() {
        return brouwerNaam;
    }
}
