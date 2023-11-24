package com.plazotechnologies.currency_expense_tracker.util.mapper;

import com.plazotechnologies.currency_expense_tracker.util.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.Mapper;
import org.mapstruct.TargetType;

import java.util.UUID;

@Mapper
public abstract class EntityMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public <T extends BaseEntity> T getEntity(UUID uuid, @TargetType Class<T> clazz) {
        return entityManager.getReference(clazz, uuid);
    }

    public <T extends BaseEntity> UUID getId(T entity) {
        return entity.getId();
    }
}
