/**
 * 
 */
package com.bus.sbud.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bus.sbud.model.Category;

/**
 * @author chaitanyam
 *
 */
public interface CategoryDAO {
	
	/**
	 * @param category
	 * @return
	 */
	public Category insert(Category category);
	/**
	 * @param category
	 * @return
	 */
	public int update(Category category);
	
	/**
	 * @param id
	 * @return
	 */
	public String findNameById(Long id);
	
	/**
	 * @param name
	 * @return
	 */
	public Long findIdByName(String name);
	
	/**
	 * @param date
	 * @return
	 */
	public List<Map<String, Object>> findTotalByCategoryForMonth(Date date);

}
