package com.plazotechnologies.currency_expense_tracker.transactions.controller;

import com.plazotechnologies.currency_expense_tracker.transactions.core.TransactionDto;
import com.plazotechnologies.currency_expense_tracker.transactions.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final ITransactionService transactionService;

    @Autowired
    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto){;
        transactionService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public TransactionDto getTransactionsByDateTime(@RequestParam String dateTime) throws UnsupportedEncodingException {
        String decodedDateTime = URLDecoder.decode(dateTime, StandardCharsets.UTF_8.toString());
        LocalDateTime parsedDateTime = LocalDateTime.parse(decodedDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        return transactionService.getTransactionByDateTime(parsedDateTime);
    }
}
