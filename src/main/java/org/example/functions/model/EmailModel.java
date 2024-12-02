package org.example.functions.model;



import org.example.functions.enums.StatusEmail;

import java.io.Serializable;
import java.time.LocalDateTime;


public class EmailModel {
    private String emailFrom;

    private String emailTo;

    private String subject;

    private String bodyEmail;




    public EmailModel(){
    }



    public EmailModel(String emailFrom,
                      String emailTo, String subject, String bodyEmail
                     ) {

        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.subject = subject;
        this.bodyEmail = bodyEmail;
    }

    public EmailModel(EmailModel emailM) {
        this.emailTo = emailM.emailTo;
        this.emailFrom = emailM.emailFrom;
        this.bodyEmail = emailM.bodyEmail;
        this.subject = emailM.subject;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBodyEmail() {
        return bodyEmail;
    }

    public void setBodyEmail(String bodyEmail) {
        this.bodyEmail = bodyEmail;
    }

}
