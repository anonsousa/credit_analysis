package com.anonsousa.credit.domain.dtos.creditproposal;

import com.anonsousa.credit.domain.enums.creditproposal.EmploymentStatus;
import com.anonsousa.credit.domain.enums.creditproposal.MaritalStatus;
import com.anonsousa.credit.domain.enums.creditproposal.ResidenceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreditProposal {

    @NotNull
    private Long userId;

    @NotNull
    @Positive
    private BigDecimal requestedAmount;

    @NotNull
    @Positive
    private int paymentPeriod;

    @NotNull
    @PositiveOrZero
    private int timeAtCurrentJob;

    @NotNull
    @PositiveOrZero
    private int dependentsNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ResidenceType residenceType;
}
