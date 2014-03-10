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
public interface TagDAO {
	
	/**
	 * @param tag
	 * @return
	 */
	public Tag insert(Tag tag);
	/**
	 * @param tag
	 * @return
	 */
	public int update(Tag tag);
	/**
	 * @param name
	 * @return
	 */
	public Long findIdByName(String name);
	/**
	 * @param id
	 * @return
	 */
	public String findNameById(Long id);
	
	/**
	 * @param searchQuery
	 * @return
	 */
	public List<Tag> findTagsBySearchQuery(String searchQuery);

}
