package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.service;

import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto.ExchangeRateResponseDto;
import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao.model.ExchangeRateData;

public interface ICurrencyService {
    void saveExchangeRateData(ExchangeRateResponseDto exchangeRateResponseDto);
    ExchangeRateData getCurrencyExchangeRate();

}
