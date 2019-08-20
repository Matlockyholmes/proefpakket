package be.vdab.proefpakket.mail;

import be.vdab.proefpakket.domain.Bestelling;

public interface MailSender {
    void nieuweAanvraag(Bestelling bestelling);
}
