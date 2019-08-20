package be.vdab.proefpakket.services;

import be.vdab.proefpakket.domain.Bestelling;
import be.vdab.proefpakket.mail.DefaultMailSender;
import be.vdab.proefpakket.repositories.BestellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultBestellingService implements BestellingService{
    private final BestellingRepository bestellingRepository;
    private final DefaultMailSender mailSender;

    public DefaultBestellingService(BestellingRepository bestellingRepository, DefaultMailSender mailSender) {
        this.bestellingRepository = bestellingRepository;
        this.mailSender = mailSender;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void createBestelling(Bestelling bestelling) {
        bestellingRepository.save(bestelling);
        mailSender.nieuweAanvraag(bestelling);
    }
}
