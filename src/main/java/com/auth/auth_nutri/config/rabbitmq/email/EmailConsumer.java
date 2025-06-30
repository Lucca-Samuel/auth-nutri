package com.auth.auth_nutri.config.rabbitmq.email;

import com.auth.auth_nutri.dto.EmailDTO;
import com.auth.auth_nutri.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.auth.auth_nutri.config.rabbitmq.RabbitMqConfig.EMAIL_SEND_QUEUE;

@Component
public class EmailConsumer {
    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = EMAIL_SEND_QUEUE)
    public void receiveEmailFromQueue(EmailDTO emailDTO) {
        try {
        emailService.sendEmail(emailDTO); // Aqui de fato é enviado o e-mail
        }catch (Exception e){
            System.err.println("Erro ao processar menssagem: " + e.getMessage());

            throw e;
        }
    }
}
