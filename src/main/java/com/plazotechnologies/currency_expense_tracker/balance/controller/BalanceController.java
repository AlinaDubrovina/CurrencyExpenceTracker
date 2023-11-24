package com.plazotechnologies.currency_expense_tracker.balance.controller;

import com.plazotechnologies.currency_expense_tracker.balance.core.BalanceDto;
import com.plazotechnologies.currency_expense_tracker.balance.service.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final IBalanceService balanceService;

    @Autowired
    public BalanceController(IBalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public BalanceDto getBalance(@RequestParam LocalDate date){
        return balanceService.createBalance(date);
    }
}
