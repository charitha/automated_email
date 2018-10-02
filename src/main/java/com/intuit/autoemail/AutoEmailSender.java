package com.intuit.autoemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AutoEmailSender implements ApplicationRunner {

    @Autowired
    EmailService emailService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        sendHtmltMail();
        sendTextMail();

    }

    private void sendTextMail() {

        String from = "cmhegde@gmail.com";
        String to = "cmhegde@gmail.com";
        String subject = "Email Spring Boot Application ";

        EmailTemplate template = new EmailTemplate("template-1.txt");

        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("user", "Cherry");
        replacements.put("today", String.valueOf(new Date()));

        String message = template.getTemplate(replacements);

        Email email = new Email(from, to, subject, message);

        emailService.send(email);
    }

    private void sendHtmltMail() {


        String from = "cmhegde@gmail.com";
        String to = "cmhegde@gmail.com";
        String subject = "Email Spring Boot Application ";

        EmailTemplate template = new EmailTemplate("template-2.html");

        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("user", "cherry");
        replacements.put("today", String.valueOf(new Date()));

        String message = template.getTemplate(replacements);

        Email email = new Email(from, to, subject, message);
        email.setHtml(true);
        emailService.send(email);
    }
}
