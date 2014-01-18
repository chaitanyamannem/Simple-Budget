/**
 * 
 */
package com.bus.sbud.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.bus.sbud.dao.CategoryDAO;

/**
 * @author chaitanyam
 * 
 */
public class Expense {

	private long id;
	private double amount;
	private Date whenCreated;
	private Date tlm;
	private Long categoryId;
	private List<String> tags;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
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
	 * @return the whenCreated
	 */
	public Date getWhenCreated() {
		return whenCreated;
	}

	/**
	 * @param whenCreated
	 *            the whenCreated to set
	 */
	public void setWhenCreated(Date whenCreated) {
		this.whenCreated = whenCreated;
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
	
	public String getCategoryName(){
		CategoryDAO categoryDAO = new CategoryDAO();
		try {
			return categoryDAO.findNameById(categoryId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
