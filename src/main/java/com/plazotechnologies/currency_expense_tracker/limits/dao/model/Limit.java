package com.plazotechnologies.currency_expense_tracker.limits.dao.model;

import com.plazotechnologies.currency_expense_tracker.util.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "limits")
public class Limit extends BaseEntity {
    @Column(name = "limit_setting_date")
    private LocalDate limitSettingDate;
    @Column(name = "limit_value")
    private BigDecimal limitValue;
    @Column(name = "limit_currency_shortname")
    private String limitCurrencyShortname;
}
