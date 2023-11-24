package com.plazotechnologies.currency_expense_tracker.transactions.dao.model;

import com.plazotechnologies.currency_expense_tracker.limits.dao.model.Limit;
import com.plazotechnologies.currency_expense_tracker.transactions.util.Category;
import com.plazotechnologies.currency_expense_tracker.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    @Column(name = "account_from")
    private int accountFrom;
    @Column(name = "account_to")
    private int accountTo;
    @Column(name = "currency_shortname")
    private String currencyShortname;
    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "limit_id")
    private Limit limit;
}
