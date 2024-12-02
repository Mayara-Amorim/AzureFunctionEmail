package org.example.functions.service;


import org.example.functions.enums.StatusEmail;
import org.example.functions.model.EmailModel;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailService{
    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender){

        this.emailSender = emailSender;
    }

    public boolean apply(EmailModel emailM) {
//        emailM.setSendEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailM.getEmailFrom());
            message.setTo(emailM.getEmailTo());
            message.setSubject(emailM.getSubject());
            message.setText(emailM.getBodyEmail());
            emailSender.send(message);
//            emailM.setStatusEmail(StatusEmail.SEND);
            return true;

        }catch (MailException e){
//            emailM.setStatusEmail(StatusEmail.ERROR);
            return false;

        }

    }

}
