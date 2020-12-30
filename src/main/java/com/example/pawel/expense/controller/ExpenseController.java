package com.example.pawel.expense.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pawel.expense.model.Expense;
import com.example.pawel.expense.repository.ExpenseRepository;

@RestController
@RequestMapping("/api")
public class ExpenseController {

	private ExpenseRepository expenseRepository;
	
	public ExpenseController(ExpenseRepository expenseRepository) {
		super();
		this.expenseRepository = expenseRepository;
	}

	@GetMapping("/expenses")
	Collection<Expense> expenses(){
		return expenseRepository.findAll();
	}
	
	@PostMapping("/expenses")
	Expense addExpense(@RequestBody Expense newExpense) {
		return expenseRepository.save(newExpense);
	}
	
}
