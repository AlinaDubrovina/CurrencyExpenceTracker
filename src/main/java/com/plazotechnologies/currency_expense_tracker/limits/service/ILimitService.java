package com.plazotechnologies.currency_expense_tracker.limits.service;

import com.plazotechnologies.currency_expense_tracker.limits.core.LimitDto;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

public interface ILimitService {
    void createLimit(LimitDto limitDto);
    void updateLimit(LocalDate limitSettingDate, LimitDto limitDto);
    LimitDto getLimitByDate(LocalDate limitSettingDate);
    LimitDto findByMonth(Month month, Year year);
    LimitDto getById(UUID id);
}
