package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto.ExchangeRateResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ExchangeRateServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private ICurrencyService currencyService;
    @InjectMocks
    private ExchangeRateService exchangeRateService;
    private static final String CURRENCY_RESOURCES_URL = "https://api.twelvedata.com/time_series?symbol=RUB/USD&interval=1day&outputsize=1&&apikey=75f3681dc08c402ebbf6dbe43c425d45";

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.readValue(anyString(), eq(ExchangeRateResponseDto.class)))
                .thenReturn(createMockExchangeRateResponseDto());
    }

    @Test
    @DisplayName("testFetchDataAndSaveToDatabase")
    public void testFetchDataAndSaveToDatabase() throws JsonProcessingException {
        String mockJsonString = "{\"meta\":{\"currency_base\":\"Russian Ruble\",\"currency_quote\":\"US Dollar\"}" +
                ",\"values\":[{\"datetime\":\"2023-12-07\",\"close\":\"0.01080\"}]}";

        when(restTemplate.getForObject(eq(CURRENCY_RESOURCES_URL), eq(String.class))).thenReturn(mockJsonString);

        exchangeRateService.fetchDataAndSaveToDatabase();

        verify(restTemplate, times(1)).getForObject(eq(CURRENCY_RESOURCES_URL), eq(String.class));
        verify(objectMapper, times(1)).readValue(eq(mockJsonString), eq(ExchangeRateResponseDto.class));
        verify(currencyService, times(1)).saveExchangeRateData(any());
    }

    private ExchangeRateResponseDto createMockExchangeRateResponseDto() throws JsonProcessingException {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = testRestTemplate.getForObject(CURRENCY_RESOURCES_URL, String.class);

        return objectMapper.readValue(json, ExchangeRateResponseDto.class);
    }
}