package org.example.functions;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.example.functions.model.EmailModel;
import org.example.functions.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerJava {
    private final EmailService emailService;

    public HttpTriggerJava() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp-mail.outlook.com");
        mailSender.setPort(587);
        mailSender.setUsername(System.getenv("USER_NAME_EMAIL_SENDER"));
        mailSender.setPassword(System.getenv("PASSWORD_EMAIL_SENDER"));
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        this.emailService = new EmailService(mailSender);
    }


    @FunctionName("HttpTriggerJava")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<EmailModel>> request,
            ExecutionContext context) {
        Optional<EmailModel> emailModelOpt = request.getBody();
        if (emailModelOpt.isEmpty()) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .body("{\"error\":\"Envie um body na requisicao\"}").build();
        }
        EmailModel emailModel = emailModelOpt.get();
        try {
            Object result = emailService.apply(emailModel);

            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(result).build();
        } catch (Exception e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .body("{\"error\":\"Ocorreu um erro ao processar a solicitacao\"}").build();
        }

    }

    public class QueueJava {
        private final EmailService emailService;

        public QueueJava() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp-mail.outlook.com");
            mailSender.setPort(587);
            mailSender.setUsername(System.getenv("USER_NAME_EMAIL_SENDER"));
            mailSender.setPassword(System.getenv("PASSWORD_EMAIL_SENDER"));
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            this.emailService = new EmailService(mailSender);
        }


        @FunctionName("QueueJava")
        public void run(
                @QueueTrigger(name = "message", queueName = "emails", connection = "MyStorageConnectionAppSetting") String message,
                final ExecutionContext context
        ) {
            EmailModel emailModel = new EmailModel("contatomayaraamorimmoreira@gmail.com", "mayara_teste@outlook.com", "Ola, isso é um teste", "Teste");
            try {
                Object result = emailService.apply(emailModel);

                context.getLogger().info("Mensagem enviada: " + message);
            } catch (Exception e) {
                context.getLogger().info("Erro: " + e.getMessage());
            }
            context.getLogger().info("Queue message: " + message);
        }
    }
}



