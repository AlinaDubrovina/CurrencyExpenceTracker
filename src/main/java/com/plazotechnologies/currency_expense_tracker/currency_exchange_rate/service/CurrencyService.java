package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.service;

import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto.ExchangeRateResponseDto;

import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto.ExchangeRateValueDto;
import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao.IExchangeRateRepository;
import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao.model.ExchangeRateData;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CurrencyService implements ICurrencyService{
    private final IExchangeRateRepository exchangeRateRepository;
    @Autowired
    public CurrencyService(IExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Transactional
    @Override
    public void saveExchangeRateData(ExchangeRateResponseDto exchangeRateResponseDto) {
        ExchangeRateData exchangeRateData = convertDtoToEntity(exchangeRateResponseDto);
        exchangeRateRepository.save(exchangeRateData);
    }

    @Transactional
    @Override
    public ExchangeRateData getCurrencyExchangeRate(){
        LocalDate currentDate = LocalDate.now();
        return exchangeRateRepository.getByDate(currentDate);
    }

    private ExchangeRateData convertDtoToEntity(ExchangeRateResponseDto exchangeRateResponseDto) {
        if (exchangeRateResponseDto == null || exchangeRateResponseDto.getMeta() == null || exchangeRateResponseDto.getValues() == null) {
            throw new IllegalArgumentException("Invalid exchange rate response data");
        }

        ExchangeRateData exchangeRateData = new ExchangeRateData();
        exchangeRateData.setCurrencyBase(exchangeRateResponseDto.getMeta().getCurrencyBase());
        exchangeRateData.setCurrencyQuote(exchangeRateResponseDto.getMeta().getCurrencyQuote());

        List<ExchangeRateValueDto> exchangeRateValueDtoList = exchangeRateResponseDto.getValues();

        if (!exchangeRateValueDtoList.isEmpty()) {
            ExchangeRateValueDto exchangeRateValueDto = exchangeRateValueDtoList.get(0);
            exchangeRateData.setDate(LocalDate.parse(exchangeRateValueDto.getDatetime()));
            exchangeRateData.setClose(stringToBigDecimal(exchangeRateValueDto.getClose()));
        }

        return exchangeRateData;
    }

    private BigDecimal stringToBigDecimal(String str) {
        return str != null ? new BigDecimal(str) : null;
    }
}