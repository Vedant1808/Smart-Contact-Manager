package com.io.scm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.io.scm.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender eMailSender;

    @Value("${spring.mail.properties.domain_name}")
    private String domainName;

    @Override
    public void sendEmail(String to, String subject, String body) {

        // only when you have domain name
        // SimpleMailMessage message = new SimpleMailMessage();
        // message.setTo(to);
        // message.setSubject(subject);
        // message.setText(body);
        // message.setFrom(domainName);
        // eMailSender.send(message);

        // use for general purpose : By SMTP
        // Gmail(https://youtu.be/ThOdWjAz5cw?si=X4JvDE6ZtlD8JntS)
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("earnwhileyousleep18@gmail.com");
        eMailSender.send(message);
    }

    @Override
    public void sendEmail(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(from); // Dynamically set the "from" address
        eMailSender.send(message);
    }

    @Override
    public void sendEmailWithHtml() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithHtml'");
    }

    @Override
    public void sendEmailWithAttachment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithAttachment'");
    }

}
