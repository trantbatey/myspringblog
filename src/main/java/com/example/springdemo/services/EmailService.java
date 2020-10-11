package com.example.springdemo.services;

import com.example.springdemo.models.Ad;
import com.example.springdemo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

//    @Value("${spring.mail.from}")
//    private String from;

    public void prepareAndSendAd(Ad ad, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom(from);
        msg.setTo(ad.getOwner().getEmail());
        msg.setSubject(subject);
        msg.setText(body);

        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void prepareAndSend(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);

        try{
//            this.emailSender.send(msg);
            new Thread(new RunnableEmail(this, msg)).start();
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}

class RunnableEmail implements Runnable {
    private EmailService emailService;
    private SimpleMailMessage msg;

    public RunnableEmail(EmailService emailService, SimpleMailMessage msg) {
        this.emailService = emailService;
        this.msg = msg;
    }

    public void run() {
        try {
            emailService.emailSender.send(msg);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}
