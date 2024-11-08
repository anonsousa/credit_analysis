package com.anonsousa.credit.domain.service;

import com.anonsousa.credit.domain.model.CreditProposalEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotifyRabbitService {

    private final RabbitTemplate rabbitTemplate;

    public NotifyRabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToQueue(CreditProposalEntity proposal, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "",  proposal);
    }
}
