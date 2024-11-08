package com.anonsousa.credit.domain.model;

import com.anonsousa.credit.domain.enums.creditproposal.EmploymentStatus;
import com.anonsousa.credit.domain.enums.creditproposal.MaritalStatus;
import com.anonsousa.credit.domain.enums.creditproposal.ResidenceType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PROPOSALS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProposalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Boolean approved;

    private boolean integrated = Boolean.TRUE;

}
