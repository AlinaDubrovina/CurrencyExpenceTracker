package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ExchangeRateResponseDto {
    @JsonProperty("meta")
    private CurrencyPairDto meta;
    @JsonProperty("values")
    private List<ExchangeRateValueDto> values;
}
