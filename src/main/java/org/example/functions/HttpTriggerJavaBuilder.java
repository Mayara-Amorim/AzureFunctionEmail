package org.example.functions;

import org.example.functions.service.EmailService;

public class HttpTriggerJavaBuilder {
    private EmailService emailService;

    public HttpTriggerJavaBuilder setEmailService(EmailService emailService) {
        this.emailService = emailService;
        return this;
    }


}