package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao;

import com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao.model.ExchangeRateData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface IExchangeRateRepository extends JpaRepository<ExchangeRateData, UUID>{
    ExchangeRateData getByDatetime(LocalDate datetime);
}