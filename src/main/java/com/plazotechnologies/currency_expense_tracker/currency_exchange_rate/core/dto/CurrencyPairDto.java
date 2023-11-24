package com.plazotechnologies.currency_expense_tracker.currency_exchange_rate.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public class CurrencyPairDto {
    @JsonProperty(value = "currency_base")
    private String currencyBase;
    @JsonProperty(value = "currency_quote")
    private String currencyQuote;
}
