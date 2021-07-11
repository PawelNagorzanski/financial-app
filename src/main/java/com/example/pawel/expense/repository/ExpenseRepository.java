package com.example.pawel.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pawel.expense.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	@Query("SELECT e FROM Expense e WHERE e.user.id = :id")
	List<Expense> findExpeseById(@Param("id") Long id);
	// List<Expense> findById(String id);
    // List<Expense> getAllExpenses(String userId);
}
