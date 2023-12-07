package com.plazotechnologies.currency_expense_tracker.limits.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public class LimitDto {
    private UUID id;
    private LocalDate limitSettingDate = LocalDate.now();
    private BigDecimal limitValue;
    private LocalDate lastDayOfMonth = limitSettingDate.withDayOfMonth(limitSettingDate.lengthOfMonth());
    private String limitCurrencyShortname;
}
