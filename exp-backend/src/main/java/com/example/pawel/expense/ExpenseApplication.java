package com.example.pawel.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.pawel.expense.repository")
public class ExpenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseApplication.class, args);
	}
	
}
