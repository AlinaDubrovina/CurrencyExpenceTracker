package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.service;

import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao.IExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CurrencyScheduledTask {
    private final IExchangeRateService exchangeRateService;

    @Autowired
    public CurrencyScheduledTask(IExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Scheduled(cron = "1 0 0 * * *")
    public void saveExchangeRatesDaily() {
        exchangeRateService.fetchDataAndSaveToDatabase();
    }
}
