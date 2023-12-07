package com.plazotechnologies.currency_expense_tracker.limits.service;

import com.plazotechnologies.currency_expense_tracker.limits.core.LimitDto;
import com.plazotechnologies.currency_expense_tracker.limits.dao.ILimitMapper;
import com.plazotechnologies.currency_expense_tracker.limits.dao.ILimitRepository;
import com.plazotechnologies.currency_expense_tracker.limits.dao.model.Limit;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

@Service
public class LimitService implements ILimitService{
    private final ILimitRepository limitRepository;
    private final ILimitMapper limitMapper;

    @Autowired
    public LimitService(ILimitRepository limitRepository, ILimitMapper limitMapper) {
        this.limitRepository = limitRepository;
        this.limitMapper = limitMapper;
    }

    @Override
    @Transactional
    public void createLimit(LimitDto limitDto){
        LocalDate limitSettingDate = LocalDate.now();
        limitDto.setLimitSettingDate(limitSettingDate);
        limitDto.setLastDayOfMonth(limitSettingDate.withDayOfMonth(limitSettingDate.lengthOfMonth()));
        Limit limit = limitMapper.toEntity(limitDto);
        limitRepository.save(limit);
    }

    @Transactional
    @Override
    public LimitDto getLimitByDate(LocalDate limitSettingDate){
        Limit limit = limitRepository.getByLimitSettingDate(limitSettingDate);
        return limitMapper.toDTO(limit);
    }

    @Transactional
    @Override
    public LimitDto getById(UUID id){
        Limit limit = limitRepository.getReferenceById(id);
        return limitMapper.toDTO(limit);
    }

    @Override
    @Transactional
    public LimitDto findByMonth(Month month, Year year) {
        Limit limit = limitRepository.findByMonth(month.getValue(), year.getValue());
        return limitMapper.toDTO(limit);
    }

    @Override
    @Transactional
    public void updateLimit(LocalDate limitSettingDate, LimitDto limitDto){
        Limit newLimit = limitRepository.getByLimitSettingDate(limitSettingDate);
        newLimit.setLimitValue(limitDto.getLimitValue());
        newLimit.setLimitSettingDate(LocalDate.now());
        limitRepository.save(newLimit);
    }
}
