package com.auth.auth_nutri.config.rabbitmq.email;

import com.auth.auth_nutri.dto.EmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.auth.auth_nutri.config.rabbitmq.RabbitMqConfig.EMAIL_SEND_QUEUE;


@Component
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmailToQueue(EmailDTO emailDTO) {
        rabbitTemplate.convertAndSend(EMAIL_SEND_QUEUE, emailDTO);
    }
}
