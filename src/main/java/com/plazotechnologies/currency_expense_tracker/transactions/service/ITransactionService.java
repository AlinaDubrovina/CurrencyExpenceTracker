package com.plazotechnologies.currency_expense_tracker.transactions.service;

import com.plazotechnologies.currency_expense_tracker.transactions.core.TransactionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ITransactionService {
    void createTransaction(TransactionDto transactionDto);
    TransactionDto getTransactionByDateTime(LocalDateTime dateTime);
    List<TransactionDto> getTransactionByDate(LocalDate date);
}
