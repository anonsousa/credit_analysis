package com.anonsousa.credit.infra.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.pending.proposal.exchange}")
    private String pendingProposalExchange;

    @Value("${rabbitmq.pending.proposal.queue}")
    private String pendingProposalQueueName;

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        return rabbitTemplate;
    }

    @Bean
    public Queue createPendingProposalQueue() {
        return QueueBuilder.durable(pendingProposalQueueName).build();
    }

    @Bean
    public FanoutExchange createPendingProposalExchange() {
        return ExchangeBuilder.fanoutExchange(pendingProposalExchange).build();
    }

    @Bean
    public Binding createPendingProposalBinding() {
        return BindingBuilder.bind(createPendingProposalQueue())
                .to(createPendingProposalExchange());
    }
}
