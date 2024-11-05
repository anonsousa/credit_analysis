package com.anonsousa.credit.controllers;

import com.anonsousa.credit.domain.dtos.creditproposal.RequestCreditProposal;
import com.anonsousa.credit.domain.model.CreditProposalEntity;
import com.anonsousa.credit.domain.service.CreditProposalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/credit/proposal")
public class CreditProposalController {

    private final CreditProposalService creditProposalService;

    public CreditProposalController(CreditProposalService creditProposalService) {
        this.creditProposalService = creditProposalService;
    }

    @PostMapping
    public ResponseEntity<CreditProposalEntity> createCreditProposal(@RequestBody @Valid RequestCreditProposal creditProposal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(creditProposalService.createCreditProposal(creditProposal));
    }
}
