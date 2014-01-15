/**
 * 
 */
package com.bus.sbud.util;

import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaitanyam
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public class PieJSON {
	
	@JsonProperty
	private Map<String,Double> pieMap;
	
	public PieJSON(Map<String,Double> pieMap){
		this.pieMap = pieMap;
	}

}
