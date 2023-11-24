package com.plazotechnologies.currency_expense_tracker.transactions.dao;

import com.plazotechnologies.currency_expense_tracker.limits.dao.ILimitMapper;
import com.plazotechnologies.currency_expense_tracker.transactions.core.TransactionDto;
import com.plazotechnologies.currency_expense_tracker.transactions.dao.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = ILimitMapper.class)
public interface ITransactionMapper {
    Transaction toEntity(TransactionDto dto);

    TransactionDto toDTO(Transaction entity);

    void map(TransactionDto dto, @MappingTarget Transaction entity);

    List<Transaction> toEntityList(List<TransactionDto> dtoList);

    List<TransactionDto> toDTOList(List<Transaction> entityList);
}
