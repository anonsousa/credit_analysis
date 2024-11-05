package com.anonsousa.credit.domain.repository;

import com.anonsousa.credit.domain.model.CreditProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditProposalRepository extends JpaRepository<CreditProposalEntity, Long> {
}
