package com.plazotechnologies.currency_expense_tracker.limits.dao;

import com.plazotechnologies.currency_expense_tracker.limits.core.LimitDto;
import com.plazotechnologies.currency_expense_tracker.limits.dao.model.Limit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ILimitMapper {
    Limit toEntity(LimitDto dto);

    LimitDto toDTO(Limit entity);

    void map(LimitDto dto, @MappingTarget Limit entity);
}
