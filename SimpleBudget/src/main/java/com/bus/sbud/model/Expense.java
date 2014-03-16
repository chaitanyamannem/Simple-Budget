/**
 * 
 */
package com.bus.sbud.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author chaitanyam
 * 
 */
public class Expense {

	private Long id;
	private double amount;
	private Date spentOn;
	// time since last modified
	private Date tlm;
	private Long categoryId;
	private String categoryName;
    private String notes;
    //TODO Trimmed notes

	private List<String> tags;

	public Expense() {

	}

	public Expense(double amount, List<String> tags, Date spentOn,
			Long categoryId) {
		this.amount = amount;
		this.tags = tags;
		this.spentOn = spentOn;
		this.categoryId = categoryId;
		tlm = new Date();

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the spentOn
	 */
	public Date getSpentOn() {
		return spentOn;
	}

	/**
	 * @param spentOn
	 *            the spentOn to set
	 */
	public void setSpentOn(Date spentOn) {
		this.spentOn = spentOn;
	}

	/**
	 * @return the tlm
	 */
	public Date getTlm() {
		return tlm;
	}

	/**
	 * @param tlm
	 *            the tlm to set
	 */
	public void setTlm(Date tlm) {
		this.tlm = tlm;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void extractTagNamesFromTagList(List<Tag> tags) {
		List<String> tagNames = new ArrayList<String>();
		for (Tag tag : tags) {
			tagNames.add(tag.getName());
		}
		setTags(tagNames);
	}

}
