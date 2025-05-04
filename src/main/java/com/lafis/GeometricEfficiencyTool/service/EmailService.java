package com.lafis.GeometricEfficiencyTool.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.UUID;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;


    public void send(String to, String subject, String link) {
        try {
            Context context = new Context();
            context.setVariable("link", link);
            String html = templateEngine.process("reset-password", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");


            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("alxfn86@gmail.com");
            helper.setText(html, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailSendException("Erro ao enviar email: " + e.getMessage());
        }
    }


    @Autowired
    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
}
