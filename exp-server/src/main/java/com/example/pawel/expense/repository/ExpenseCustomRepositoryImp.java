//package com.example.pawel.expense.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import com.example.pawel.expense.model.Expense;
//
//public class ExpenseCustomRepositoryImp implements ExpenseCustomRepository{
//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	@Override
//	public List<Expense> getExpenseWithCustomQuery(){
//		List<Expense> expenseList = entityManager.createQuery("Select * from expense WHERE user_id = 1").getResultList();
//		return expenseList;
//	}
//	
//}
