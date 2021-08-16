package org.dasl.demo.accounts.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.dasl.demo.accounts.domain.Income;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsEndpoint {

	static List<Income> incomeList = new ArrayList<Income>();

	static {
		for (int i = 1; i <= 10; i++) {
			Double min = 2120.00;
			Double max = 15000.00;
			BigDecimal ingreso = new BigDecimal(min + (Double) (Math.random() * ((max - min) + 1))).setScale(2, RoundingMode.HALF_UP);
			incomeList.add(new Income("Ingreso " + i + ": ", ingreso.doubleValue()));
		}
	}

	@GetMapping("/incomes")
	public List<Income> getAllIncomes() {
		return incomeList;
	}
}
