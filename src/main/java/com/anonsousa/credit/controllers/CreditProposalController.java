package com.anonsousa.credit.controllers;

import com.anonsousa.credit.domain.dtos.creditproposal.RequestCreditProposal;
import com.anonsousa.credit.domain.dtos.creditproposal.ResponseCreditProposal;
import com.anonsousa.credit.domain.service.CreditProposalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/credit/proposal")
public class CreditProposalController {

    private final CreditProposalService creditProposalService;

    public CreditProposalController(CreditProposalService creditProposalService) {
        this.creditProposalService = creditProposalService;
    }

    @PostMapping
    public ResponseEntity<ResponseCreditProposal> createCreditProposal(@RequestBody @Valid RequestCreditProposal creditProposal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(creditProposalService.createCreditProposal(creditProposal));
    }

    @GetMapping("/{proposalId}")
    public ResponseEntity<ResponseCreditProposal> getCreditProposal(@PathVariable Long proposalId) {
        return ResponseEntity.status(HttpStatus.OK).body(creditProposalService.getCreditProposalById(proposalId));
    }
}
