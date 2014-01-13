
package com.bus.sbud.util;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaitanyam
 *
 */
public class AutoCompleteJson {
	@JsonProperty
	public String query;
	@JsonProperty
	public String[] suggestions;
	@JsonProperty
	public long[] data;
	
	public AutoCompleteJson(String query, String[] suggestions, long[] data){
		this.query = query;
		this.suggestions = suggestions;
		this.data = data;
	}

}
