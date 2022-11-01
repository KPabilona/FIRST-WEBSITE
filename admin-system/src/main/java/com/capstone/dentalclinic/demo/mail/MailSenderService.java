package com.capstone.dentalclinic.demo.mail;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailSenderService implements MailSender{

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendConfirmationMail(String from, String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo("Johndavidcabal000@gmail.com"); // business Email
            helper.setFrom(from); // email of an employee/personel
            helper.setSubject("New Employee Account");
            helper.setText(email, true);
            javaMailSender.send(message);
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Async
    public void sendApproveRegistration(String to, String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(to);
            helper.setFrom("Johndavidcabal000@gmail.com");
            helper.setSubject("Request Approved");
            helper.setText(email, true);
            javaMailSender.send(message);
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
