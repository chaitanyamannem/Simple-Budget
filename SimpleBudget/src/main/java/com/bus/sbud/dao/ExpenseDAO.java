/**
 * 
 */
package com.bus.sbud.dao;

import java.util.Date;
import java.util.List;

import com.bus.sbud.model.Expense;

/**
 * @author chaitanyam
 *
 */
public interface ExpenseDAO {
	
	/**
	 * Saves an expense
	 * @param expense
	 * @return
	 */
	public Expense insert(Expense expense);
	
	/**
	 * @param expense
	 * @return - the number of rows affected
	 */
	public int update(Expense expense);
	
	/**
	 * @param id 
	 * @return
	 */
	public Expense findById(Long id);
	
	/**
	 * @param onDate
	 * @return
	 */
	public List<Expense> findAllExpensesByDate(Date onDate);

}
