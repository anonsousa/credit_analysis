package com.anonsousa.credit.domain.scheduler;


import com.anonsousa.credit.domain.repository.CreditProposalRepository;
import com.anonsousa.credit.domain.service.NotifyRabbitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class JobRetryProposalWithoutIntegration {

    private final CreditProposalRepository creditProposalRepository;
    private final NotifyRabbitService notifyRabbitService;
    private final String exchange;

    private static final Logger logger = LoggerFactory.getLogger(JobRetryProposalWithoutIntegration.class);


    public JobRetryProposalWithoutIntegration(CreditProposalRepository creditProposalRepository,
                                              NotifyRabbitService notifyRabbitService,
                                              @Value("${rabbitmq.pending.proposal.exchange}") String exchange) {
        this.creditProposalRepository = creditProposalRepository;
        this.notifyRabbitService = notifyRabbitService;
        this.exchange = exchange;
    }


    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    public void searchForProposalsWithoutIntegration() {
        logger.info("Searching for proposals without integration on system");
        creditProposalRepository.findByIntegratedIsFalse().forEach(proposal -> {
            try {
                notifyRabbitService.sendToQueue(proposal, exchange);
                proposal.setIntegrated(true);
                creditProposalRepository.save(proposal);
            } catch (RuntimeException e) {
                proposal.setIntegrated(false);
                creditProposalRepository.save(proposal);
                logger.error(e.getMessage());
            }
        });
    }
}
