/**
 * 
 */
package com.bus.sbud.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.sbud.dao.TagDAO;
import com.bus.sbud.model.Tag;
import com.bus.sbud.service.AutoCompleteService;
import com.bus.sbud.util.AutoCompleteJson;

/**
 * @author chaitanyam
 * 
 */
@Service
public class AutoCompleteServiceImpl implements AutoCompleteService {

	@Autowired
	TagDAO tagDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bus.sbud.service.AutoCompleteService#getTagSuggestions(java.lang.
	 * String)
	 */
	public AutoCompleteJson getTagSuggestions(String query) {
		List<String> suggestions = new ArrayList<String>();
		List<Long> data = new ArrayList<Long>();
		List<Tag> tags = tagDAO.findTagsBySearchQuery(query);
		for (Tag tag : tags) {
			suggestions.add(tag.getName());
			data.add(tag.getId());
		}
		return new AutoCompleteJson(query, suggestions, data);

	}
}
