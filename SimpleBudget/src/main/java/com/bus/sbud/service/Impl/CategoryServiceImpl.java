/**
 * 
 */
package com.bus.sbud.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.sbud.dao.CategoryDAO;
import com.bus.sbud.service.CategoryService;

/**
 * @author chaitanyam
 * 
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO categoryDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bus.sbud.service.CategoryService#getCategoryName(long)
	 */
	public String getCategoryName(long id) {
		return categoryDAO.findNameById(id);
	}

}
