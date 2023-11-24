package com.plazotechnologies.currency_expense_tracker.limits.controller;

import com.plazotechnologies.currency_expense_tracker.limits.core.LimitDto;
import com.plazotechnologies.currency_expense_tracker.limits.service.ILimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/limit")
public class LimitController {
    private final ILimitService limitService;

    @Autowired
    public LimitController(ILimitService limitService) {
        this.limitService = limitService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createLimit (@RequestBody LimitDto limitDto){;
        limitService.createLimit(limitDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/{limitSettingDate}", method = RequestMethod.PUT)
    public void update(@PathVariable("limitSettingDate")LocalDate limitSettingDate,
                       @RequestBody LimitDto limitDto) {
        limitService.updateLimit(limitSettingDate, limitDto);
    }
}
