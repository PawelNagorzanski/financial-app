package com.example.pawel.expense.model;

import java.time.Instant;
import javax.persistence.*;

import com.example.pawel.expense.model.User;
import com.example.pawel.expense.model.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@NoArgsConstructor
//@Data
//@Table(name="expense")
@Entity
public class Expense {

//	@Id
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	private Instant expensedate; 

	private String description;

//	@ManyToOne
//	private Category category;

//	@JsonIgnore
//	@ManyToOne
//	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getExpensedate() {
		return expensedate;
	}

	public void setExpensedate(Instant expensedate) {
		this.expensedate = expensedate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
}