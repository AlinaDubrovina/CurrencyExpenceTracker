package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CurrencyScheduledTask {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public CurrencyScheduledTask(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void saveExchangeRatesDaily() {
        exchangeRateService.fetchDataAndSaveToDatabase();
    }
}
