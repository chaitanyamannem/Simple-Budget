/**
 * 
 */
package com.bus.sbud.dao;

import java.util.List;

import com.bus.sbud.model.Tag;

/**
 * @author chaitanyam
 *
 */
public interface ExpenseTagDAO {
	
	/**
	 * @param expenseId
	 * @param tagId
	 * @return
	 */
	public int insert(Long expenseId, Long tagId);
	
	/**
	 * @param expenseId
	 * @param tagId
	 * @return
	 */
	public int delete(Long expenseId, Long tagId);
	
	/**
	 * Gets all Tag ids linked with the expense 
	 * and keeps it in tag model object.
	 * @param expenseId
	 * @return - List of tag model objects
	 */
	public List<Tag> findsAllTagsByExpenseId(Long expenseId);

}
