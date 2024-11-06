package com.anonsousa.credit.domain.dtos.creditproposal;

import com.anonsousa.credit.domain.enums.creditproposal.EmploymentStatus;
import com.anonsousa.credit.domain.enums.creditproposal.MaritalStatus;
import com.anonsousa.credit.domain.enums.creditproposal.ResidenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreditProposal {

    private Long id;
    private Long userId;
    private BigDecimal requestedAmount;
    private int paymentPeriod;
    private int timeAtCurrentJob;
    private int dependentsNumber;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;
    @Enumerated(EnumType.STRING)
    private ResidenceType residenceType;
}
