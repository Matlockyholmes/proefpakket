package be.vdab.proefpakket.mail;

import be.vdab.proefpakket.domain.Bestelling;

public interface MailSender {
    void nieuweAanvraag(String emailadres, String brouwernaam);
}
