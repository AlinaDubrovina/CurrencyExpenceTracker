package com.plazotechnologies.currency_expense_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExpenseTrackerApplication.class, args);
	}
}
