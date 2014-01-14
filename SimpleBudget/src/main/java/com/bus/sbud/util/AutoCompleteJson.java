package com.bus.sbud.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaitanyam
 * 
 */
public class AutoCompleteJson {
	@JsonProperty
	public String query;
	@JsonProperty
	public List<String> suggestions;
	@JsonProperty
	public List<Long> data;

	public AutoCompleteJson(String query, List<String> suggestions,
			List<Long> data) {
		this.query = query;
		this.suggestions = suggestions;
		this.data = data;
	}

}
