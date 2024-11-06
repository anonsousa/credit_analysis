package com.anonsousa.credit.domain.mappers;

import com.anonsousa.credit.domain.dtos.creditproposal.RequestCreditProposal;
import com.anonsousa.credit.domain.dtos.creditproposal.ResponseCreditProposal;
import com.anonsousa.credit.domain.model.CreditProposalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditProposalMapper {

    CreditProposalMapper INSTANCE = Mappers.getMapper(CreditProposalMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "integrated", ignore = true)
    CreditProposalEntity convertDtoToEntity(RequestCreditProposal creditProposal);


    ResponseCreditProposal convertEntityToDto(CreditProposalEntity creditProposal);
}
