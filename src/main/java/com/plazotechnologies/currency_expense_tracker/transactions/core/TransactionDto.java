package com.plazotechnologies.currency_expense_tracker.transactions.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.plazotechnologies.currency_expense_tracker.limits.core.LimitDto;
import com.plazotechnologies.currency_expense_tracker.transactions.util.Category;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@RequiredArgsConstructor
public class TransactionDto {
    private UUID id;
    private int accountFrom;
    private int accountTo;
    private String currencyShortname;
    private BigDecimal transactionAmount;
    private Category category;
    private LocalDateTime dateTime = LocalDateTime.now();
    private LimitDto limit;
}
