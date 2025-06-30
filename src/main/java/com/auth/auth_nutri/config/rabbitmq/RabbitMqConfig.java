package com.auth.auth_nutri.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String EMAIL_SEND_QUEUE = "email_queue";
    public static final String EMAIL_DEAD_LETTER_QUEUE = "email_dead_letter_queue";
    public static final String EMAIL_EXCHANGE = "email_exchange";
    public static final String DEAD_LETTER_EXCHANGE = "dead_letter_exchange";

//    @Bean
//    public Declarable emailSenderQueue(){
//        return new Queue(EMAIL_SEND_QUEUE);
//    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(EMAIL_EXCHANGE);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public Queue emailSenderQueue() {
        return QueueBuilder.durable(EMAIL_SEND_QUEUE)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)    // troca para DLX
                .withArgument("x-dead-letter-routing-key", EMAIL_DEAD_LETTER_QUEUE)  // fila de DLQ
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(EMAIL_DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailSenderQueue())
                .to(emailExchange())
                .with("email_routing_key");
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with(EMAIL_DEAD_LETTER_QUEUE);
    }
}
