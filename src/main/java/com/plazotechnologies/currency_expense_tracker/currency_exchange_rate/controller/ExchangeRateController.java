package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.controller;

import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao.model.ExchangeRateData;
import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.service.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {
    private final ICurrencyService currencyService;
    @Autowired
    public ExchangeRateController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ExchangeRateData getCurrencyExchangeRate() {
        return currencyService.getCurrencyExchangeRate();
    }
}