package com.anonsousa.credit.domain.service;

import com.anonsousa.credit.domain.dtos.creditproposal.RequestCreditProposal;
import com.anonsousa.credit.domain.dtos.creditproposal.ResponseCreditProposal;
import com.anonsousa.credit.domain.mappers.CreditProposalMapper;
import com.anonsousa.credit.domain.model.CreditProposalEntity;
import com.anonsousa.credit.domain.model.UserEntity;
import com.anonsousa.credit.domain.repository.CreditProposalRepository;
import com.anonsousa.credit.domain.repository.UserRepository;
import com.anonsousa.credit.infra.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreditProposalService {

    private final CreditProposalRepository creditProposalRepository;
    private final UserRepository userRepository;
    private final NotifyRabbitService notifyRabbitService;
    private final String exchange;

    public CreditProposalService(CreditProposalRepository creditProposalRepository,
                                 UserRepository userRepository,
                                 NotifyRabbitService notifyRabbitService,
                                 @Value("${rabbitmq.pending.proposal.exchange}") String exchange) {
        this.creditProposalRepository = creditProposalRepository;
        this.userRepository = userRepository;
        this.notifyRabbitService = notifyRabbitService;
        this.exchange = exchange;
    }

    @Transactional
    public ResponseCreditProposal createCreditProposal(RequestCreditProposal creditProposal){
        Optional<UserEntity> user = userRepository.findById(creditProposal.getUserId());

        if(user.isPresent()){

            CreditProposalEntity creditProposalEntity = CreditProposalMapper.INSTANCE.convertDtoToEntity(creditProposal);
            creditProposalEntity = creditProposalRepository.save(creditProposalEntity);

            notifyRabbitQueue(creditProposalEntity);

            return CreditProposalMapper.INSTANCE.convertEntityToDto(creditProposalEntity);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional(readOnly = true)
    public ResponseCreditProposal getCreditProposalById(Long proposalId){
        CreditProposalEntity creditProposal = creditProposalRepository
                .findById(proposalId).orElseThrow(() -> new ResourceNotFoundException("CreditProposal not found"));

        return CreditProposalMapper.INSTANCE.convertEntityToDto(creditProposal);
    }

    public void notifyRabbitQueue(CreditProposalEntity proposal) {
        try{
            notifyRabbitService.sendToQueue(proposal, exchange);
        } catch (RuntimeException exception) {
            proposal.setIntegrated(false);
            creditProposalRepository.save(proposal);
        }

    }
}
