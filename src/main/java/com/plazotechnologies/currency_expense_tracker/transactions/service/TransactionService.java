package com.plazotechnologies.currency_expense_tracker.transactions.service;

import com.plazotechnologies.currency_expense_tracker.limits.core.LimitDto;
import com.plazotechnologies.currency_expense_tracker.limits.dao.ILimitMapper;
import com.plazotechnologies.currency_expense_tracker.limits.dao.model.Limit;
import com.plazotechnologies.currency_expense_tracker.limits.service.ILimitService;
import com.plazotechnologies.currency_expense_tracker.transactions.core.TransactionDto;
import com.plazotechnologies.currency_expense_tracker.transactions.dao.ITransactionMapper;
import com.plazotechnologies.currency_expense_tracker.transactions.dao.ITransactionRepository;
import com.plazotechnologies.currency_expense_tracker.transactions.dao.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Service
public class TransactionService implements ITransactionService{
    private final ITransactionRepository transactionRepository;
    private final ILimitService limitService;
    private final ILimitMapper limitMapper;
    private final ITransactionMapper transactionMapper;

    @Autowired
    public TransactionService(ITransactionRepository transactionRepository, ILimitService limitService, ILimitMapper limitMapper, ITransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.limitService = limitService;
        this.limitMapper = limitMapper;
        this.transactionMapper = transactionMapper;
    }

    @Transactional
    @Override
    public void createTransaction(TransactionDto transactionDto) {
        LocalDate currentDate = transactionDto.getDateTime().toLocalDate();
        LimitDto limitDto = limitService.findByMonth(currentDate.getMonth(), Year.of(currentDate.getYear()));
        Limit limit = limitMapper.toEntity(limitDto);

        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setLimit(limit);
        transactionRepository.save(transaction);

        LimitDto updatedLimit = limitService.getById(transaction.getLimit().getId());
        updatedLimit.setLimitValue(limitDto.getLimitValue().subtract(transactionDto.getTransactionAmount()));
        limitService.updateLimit(transaction.getLimit().getLimitSettingDate(), updatedLimit);
    }

    @Transactional
    @Override
    public List<TransactionDto> getTransactionByDate(LocalDate date){
        List<Transaction> transactions = transactionRepository.findTransactionsByDate(date);
        return transactionMapper.toDTOList(transactions);
    }

    @Transactional
    @Override
    public TransactionDto getTransactionByDateTime(LocalDateTime dateTime) {
        Transaction transaction = transactionRepository.findByDateTime(dateTime);
        return transactionMapper.toDTO(transaction);
    }
}
