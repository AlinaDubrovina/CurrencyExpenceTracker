package com.plazotechnologies.currency_expense_tracker.limits.dao;

import com.plazotechnologies.currency_expense_tracker.limits.dao.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;

public interface ILimitRepository extends JpaRepository<Limit, UUID> {
    Limit getByLimitSettingDate(LocalDate limitSettingDate);
    @Query("SELECT l FROM Limit l WHERE EXTRACT(MONTH FROM l.limitSettingDate) = :month AND EXTRACT(YEAR FROM l.limitSettingDate) = :year")
    Limit findByMonth(@Param("month") int month, @Param("year") int year);
}
