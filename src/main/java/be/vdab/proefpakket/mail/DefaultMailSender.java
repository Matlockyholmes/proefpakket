package be.vdab.proefpakket.mail;

import be.vdab.proefpakket.domain.Bestelling;
import be.vdab.proefpakket.exceptions.KanMailNietVersturenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class DefaultMailSender implements MailSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender javaMailSender;

    public DefaultMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void nieuweAanvraag(String emailadres, String brouwernaam) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(emailadres);
            helper.setText("Bedankt voor uw interesse. U ontvangt uw proefpakket " + brouwernaam + " binnenkort.");
            javaMailSender.send(message);
        } catch (MessagingException ex) {
            logger.error("Kan mail nieuwe bestelling niet verzenden",ex);
            throw new KanMailNietVersturenException();
        }

    }
}
