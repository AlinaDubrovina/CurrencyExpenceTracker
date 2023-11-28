package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.dao.model;


import com.plazotechnologies.currency_expense_tracker.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "exchange_rates")
public class ExchangeRateData extends BaseEntity {
    @Column(name = "currency_base")
    private String currencyBase;
    @Column(name = "currency_quote")
    private String currencyQuote;
    private LocalDate date;
    private BigDecimal close;
}
