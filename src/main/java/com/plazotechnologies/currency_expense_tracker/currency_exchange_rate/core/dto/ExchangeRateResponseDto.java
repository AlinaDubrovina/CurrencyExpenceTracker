package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ExchangeRateResponseDto {
    @JsonProperty("meta")
    private CurrencyPairDto meta;
    @JsonProperty("values")
    private List<ExchangeRateValueDto> values;
}
