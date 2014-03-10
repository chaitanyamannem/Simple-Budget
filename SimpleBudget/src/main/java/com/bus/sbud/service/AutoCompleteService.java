/**
 * 
 */
package com.bus.sbud.service;

import com.bus.sbud.util.AutoCompleteJson;

/**
 * @author chaitanyam
 *
 */
public interface AutoCompleteService {
	
	/**
	 * @param query
	 * @return
	 */
	public AutoCompleteJson getTagSuggestions(String query);

}
