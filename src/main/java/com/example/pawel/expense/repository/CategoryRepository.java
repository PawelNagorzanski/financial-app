package com.example.pawel.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pawel.expense.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);
	
}
