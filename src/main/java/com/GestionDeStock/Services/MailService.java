package com.GestionDeStock.Services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ResourceLoader resourceLoader;

    public void sendEmail(String to, String subject, String content) throws MessagingException, IOException, jakarta.mail.MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        emailSender.send(message);
    }
}
