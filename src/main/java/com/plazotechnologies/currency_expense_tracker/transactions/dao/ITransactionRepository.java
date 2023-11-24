package com.plazotechnologies.currency_expense_tracker.transactions.dao;

import com.plazotechnologies.currency_expense_tracker.transactions.dao.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ITransactionRepository extends JpaRepository<Transaction, UUID> {
    Transaction findByDateTime(LocalDateTime dateTime);
    @Query("SELECT t FROM Transaction t WHERE CAST(t.dateTime AS date) = :date")
    List<Transaction> findTransactionsByDate(@Param("date") LocalDate date);
}
