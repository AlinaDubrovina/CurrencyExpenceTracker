package com.plazotechnologies.currency_expense_tracker.balance.service;

import com.plazotechnologies.currency_expense_tracker.balance.core.BalanceDto;

import java.time.LocalDate;

public interface IBalanceService {
    BalanceDto createBalance(LocalDate transactionsDate);
}
