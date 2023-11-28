package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ExchangeRateValueDto {
    private String datetime;
    private String close;
}
