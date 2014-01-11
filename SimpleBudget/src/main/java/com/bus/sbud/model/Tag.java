/**
 * 
 */
package com.bus.sbud.model;

/**
 * @author chaitanyam
 *
 */
public class Tag {
	
	private long id;
	private String name;
	
	public Tag(String name){
		this.name = name;
	}
	
	public Tag(){
		
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}
