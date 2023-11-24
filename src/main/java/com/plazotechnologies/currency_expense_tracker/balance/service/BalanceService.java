package com.plazotechnologies.currency_expense_tracker.balance.service;

import com.plazotechnologies.currency_expense_tracker.balance.core.BalanceDto;
import com.plazotechnologies.currency_expense_tracker.limits.core.LimitDto;
import com.plazotechnologies.currency_expense_tracker.limits.service.ILimitService;
import com.plazotechnologies.currency_expense_tracker.transactions.core.TransactionDto;
import com.plazotechnologies.currency_expense_tracker.transactions.service.ITransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
public class BalanceService implements IBalanceService{
    private final ITransactionService transactionService;
    private final ILimitService limitService;
    @Autowired
    public BalanceService(ITransactionService transactionService, ILimitService limitService) {
        this.transactionService = transactionService;
        this.limitService = limitService;
    }

    @Transactional
    @Override
    public BalanceDto createBalance(LocalDate transactionsDate) {
        boolean limitExceed = false;
        LimitDto limit = limitService.findByMonth(transactionsDate.getMonth(), Year.of(transactionsDate.getYear()));

        List<TransactionDto> transactions = transactionService.getTransactionByDate(transactionsDate);
        if (limit.getLimitValue().compareTo(BigDecimal.ZERO) < 0){
            limitExceed = true;
        }
        return new BalanceDto(transactions, limitExceed);
    }
}
