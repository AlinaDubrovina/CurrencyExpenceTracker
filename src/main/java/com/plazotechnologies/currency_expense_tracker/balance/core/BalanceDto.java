package com.plazotechnologies.currency_expense_tracker.balance.core;

import com.plazotechnologies.currency_expense_tracker.transactions.core.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private List<TransactionDto> transactions;
    private boolean limitExceed;
}
