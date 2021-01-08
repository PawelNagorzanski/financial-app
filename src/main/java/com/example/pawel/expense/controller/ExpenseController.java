package com.example.pawel.expense.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pawel.expense.model.Category;
import com.example.pawel.expense.model.Expense;
import com.example.pawel.expense.repository.ExpenseRepository;

@RestController
@RequestMapping("/api")
@Controller
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
	
	@GetMapping("/expense/{id}")
	ResponseEntity<Expense> getExpense(@PathVariable Long id) {
		Optional<Expense> _expense = expenseRepository.findById(id);
		
		if (_expense.isPresent()) {
			return new ResponseEntity<>(_expense.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/expense")
	ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) throws URISyntaxException {
		Expense result = expenseRepository.save(expense);
		return ResponseEntity.created(new URI("/api/expense/" + result.getId())).body(result);
	}
	
	@DeleteMapping("/expenses/{id}")
	ResponseEntity<?> deleteExpense(@PathVariable Long id) {
		try {
			expenseRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
}
