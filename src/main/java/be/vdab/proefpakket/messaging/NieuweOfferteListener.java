package be.vdab.proefpakket.messaging;

import be.vdab.proefpakket.domain.Bestelling;
import be.vdab.proefpakket.mail.MailSender;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NieuweOfferteListener {
    private final MailSender mailSender;

    public NieuweOfferteListener(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @JmsListener(destination = "${nieuweAanvraagQueue}")
    void ontvangBoodschap(NieuweBestelling nieuweBestelling){
        mailSender.nieuweAanvraag(nieuweBestelling.getEmailadres(), nieuweBestelling.getBrouwerNaam());
    }
}
