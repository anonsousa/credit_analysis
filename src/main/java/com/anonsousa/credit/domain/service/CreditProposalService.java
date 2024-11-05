package com.anonsousa.credit.domain.service;

import com.anonsousa.credit.domain.dtos.creditproposal.RequestCreditProposal;
import com.anonsousa.credit.domain.mappers.CreditProposalMapper;
import com.anonsousa.credit.domain.model.CreditProposalEntity;
import com.anonsousa.credit.domain.model.UserEntity;
import com.anonsousa.credit.domain.repository.CreditProposalRepository;
import com.anonsousa.credit.domain.repository.UserRepository;
import com.anonsousa.credit.infra.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreditProposalService {

    private final CreditProposalRepository creditProposalRepository;
    private final UserRepository userRepository;

    public CreditProposalService(CreditProposalRepository creditProposalRepository, UserRepository userRepository) {
        this.creditProposalRepository = creditProposalRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CreditProposalEntity createCreditProposal(RequestCreditProposal creditProposal){
        Optional<UserEntity> user = userRepository.findById(creditProposal.getUserId());

        if(user.isPresent()){
            CreditProposalEntity creditProposalEntity = CreditProposalMapper.INSTANCE.convertDtoToEntity(creditProposal);

            return creditProposalRepository.save(creditProposalEntity);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
