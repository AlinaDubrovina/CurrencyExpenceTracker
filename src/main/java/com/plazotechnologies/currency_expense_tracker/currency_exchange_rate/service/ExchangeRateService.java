package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto.ExchangeRateResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class ExchangeRateService implements IExchangeRateService{
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ICurrencyService currencyService;
    private static final String CURRENCY_RESOURCES_URL = "https://api.twelvedata.com/time_series?symbol=RUB/USD&interval=1day&outputsize=1&&apikey=75f3681dc08c402ebbf6dbe43c425d45";

    @Autowired
    public ExchangeRateService(RestTemplate restTemplate, ObjectMapper objectMapper, ICurrencyService currencyService) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.currencyService = currencyService;
    }

    @Transactional
    @Override
    public void fetchDataAndSaveToDatabase() {
        String jsonString = restTemplate.getForObject(CURRENCY_RESOURCES_URL, String.class);
        try {
            ExchangeRateResponseDto exchangeRateResponseDto = objectMapper.readValue(jsonString, ExchangeRateResponseDto.class);
            currencyService.saveExchangeRateData(exchangeRateResponseDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
