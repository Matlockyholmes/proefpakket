package be.vdab.proefpakket.services;

import be.vdab.proefpakket.domain.Bestelling;
import be.vdab.proefpakket.mail.DefaultMailSender;
import be.vdab.proefpakket.messaging.NieuweBestelling;
import be.vdab.proefpakket.repositories.BestellingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultBestellingService implements BestellingService{
    private final BestellingRepository bestellingRepository;
    private final DefaultMailSender mailSender;
    private final JmsTemplate jmsTemplate;
    private final String nieuweAanvraagQueue;

    public DefaultBestellingService(BestellingRepository bestellingRepository, DefaultMailSender mailSender, JmsTemplate jmsTemplate, @Value("${nieuweAanvraagQueue}") String nieuweAanvraagQueue) {
        this.bestellingRepository = bestellingRepository;
        this.mailSender = mailSender;
        this.jmsTemplate = jmsTemplate;
        this.nieuweAanvraagQueue = nieuweAanvraagQueue;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void createBestelling(Bestelling bestelling) {
        bestellingRepository.save(bestelling);
        NieuweBestelling nieuweBestelling = new NieuweBestelling(bestelling.getEmailAdres(), bestelling.getBrouwer().getNaam());
        jmsTemplate.convertAndSend(nieuweAanvraagQueue, nieuweBestelling);
    }
}
