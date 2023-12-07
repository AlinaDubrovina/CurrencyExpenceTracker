package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto.ExchangeRateResponseDto;
import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao.IExchangeRateRepository;
import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao.model.ExchangeRateData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class CurrencyServiceTest {
    @MockBean
    IExchangeRateRepository exchangeRateRepository;
    @Autowired
    CurrencyService currencyService;
    private static final String CURRENCY_RESOURCES_URL = "https://api.twelvedata.com/time_series?symbol=RUB/USD&interval=1day&outputsize=1&&apikey=75f3681dc08c402ebbf6dbe43c425d45";

    @Test
    @DisplayName("getCurrencyExchangeRate")
    public void testGetCurrencyExchangeRate() throws JsonProcessingException {
        LocalDate date = LocalDate.now();

        ExchangeRateData mockExchangeRateData = createExchangeRateData();
        when(exchangeRateRepository.getByDate(date)).thenReturn(mockExchangeRateData);

        ExchangeRateData result = currencyService.getCurrencyExchangeRate();

        assertNotNull(result);
        assertEquals(result, mockExchangeRateData);

        verify(exchangeRateRepository, times(1)).getByDate(date);
    }

    @Test
    @DisplayName("testSaveExchangeRateData")
    void testSaveExchangeRateData() throws JsonProcessingException {
        ExchangeRateResponseDto exchangeRateResponseDto = createExchangeRateResponseDto();

        ExchangeRateData exchangeRateData = createExchangeRateData();

        when(exchangeRateRepository.save(any(ExchangeRateData.class))).thenReturn(exchangeRateData);

        currencyService.saveExchangeRateData(exchangeRateResponseDto);

        verify(exchangeRateRepository, times(1)).save(any(ExchangeRateData.class));
    }

    private ExchangeRateData createExchangeRateData() throws JsonProcessingException {
        LocalDate localDate = LocalDate.now();
        ExchangeRateResponseDto exchangeRateResponseDto = createExchangeRateResponseDto();
        BigDecimal toBigdecimal = new BigDecimal(exchangeRateResponseDto.getValues().get(0).getClose());
        return new ExchangeRateData(exchangeRateResponseDto.getMeta().getCurrencyBase(), exchangeRateResponseDto.getMeta().getCurrencyQuote(),
                localDate, toBigdecimal);
    }
    private ExchangeRateResponseDto createExchangeRateResponseDto() throws JsonProcessingException {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = testRestTemplate.getForObject(CURRENCY_RESOURCES_URL, String.class);

        return objectMapper.readValue(json, ExchangeRateResponseDto.class);
    }
}