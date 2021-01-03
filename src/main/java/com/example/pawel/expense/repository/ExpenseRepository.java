package com.example.pawel.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pawel.expense.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	

}
