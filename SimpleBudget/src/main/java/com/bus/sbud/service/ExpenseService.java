package com.bus.sbud.service;

import java.util.List;

import com.bus.sbud.model.Expense;


/**
 * @author chaitanyam
 *
 */

public interface ExpenseService {
	
	/**
	 * Adds a single expense
	 * 
	 * @param amount
	 * @param tags
	 * @param spentOnDate
	 * @param category
	 */
	public void addAnExpense(double amount, String tags, String spentOnDate, String category);
	

	/**
	 * @param id
	 * @param amount
	 * @param tags
	 * @param spentOnDate
	 * @param category
	 */
	public void editAnExpense(long id, double amount, String tags, String spentOnDate, String category);
	
	/**
	 * @param onDate
	 * @return
	 */
	public List<Expense> getExpensesByDate(String onDate);

	/**
	 * @param expenses
	 * @return
	 */
	public double getTotalOfAllExpenses(List<Expense> expenses);
}
