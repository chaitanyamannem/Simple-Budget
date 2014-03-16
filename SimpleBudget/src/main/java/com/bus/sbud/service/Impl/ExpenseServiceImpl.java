/**
 * 
 */
package com.bus.sbud.service.Impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.sbud.dao.CategoryDAO;
import com.bus.sbud.dao.ExpenseDAO;
import com.bus.sbud.dao.ExpenseTagDAO;
import com.bus.sbud.dao.TagDAO;
import com.bus.sbud.model.Expense;
import com.bus.sbud.model.Tag;
import com.bus.sbud.service.ExpenseService;
import com.bus.sbud.util.DateUtil;

/**
 * @author chaitanyam
 * 
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {

	private static final Logger logger = Logger.getLogger("ExpenseServiceImpl");

	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	TagDAO tagDAO;
	@Autowired
	ExpenseTagDAO expenseTagDAO;
	@Autowired
	ExpenseDAO expenseDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bus.sbud.service.ExpenseService#addExpense(double,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addAnExpense(double amount, String tags, String spentOnDate,
			String category, String notes) {
		// tags
		String[] tagsArray = StringUtils.split(tags, ',');
		List<String> tagsList = Arrays.asList(tagsArray);

		// spentOn - if empty use today as default
		Date spentOn = new Date();
		if (!StringUtils.isEmpty(spentOnDate)) {
			Date parsedDate = DateUtil.parseDate(spentOnDate,
					DateUtil.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH);
			spentOn = parsedDate;
		}
		logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		logger.info("SpentON" + spentOn);
		logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		// category
		Long categoryId = categoryDAO.findIdByName(category);

		// save the expense
		Expense expense = new Expense(amount, tagsList, spentOn, categoryId);
		// notes
		if (StringUtils.isNotEmpty(notes)) {
			expense.setNotes(notes);
		}
		expenseDAO.insert(expense);
		// link the tags to expense
		for (String tagName : tagsList) {
			Long tagId = tagDAO.findIdByName(tagName);
			if (tagId == null) {
				Tag tag = new Tag(tagName);
				tagId = tagDAO.insert(tag).getId();
			}
			expenseTagDAO.insert(expense.getId(), tagId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bus.sbud.service.ExpenseService#editAnExpense(double,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public void editAnExpense(long id, double amount, String tags,
			String spentOnDate, String category) {
		// tags
		String[] tagsArray = StringUtils.split(tags, ',');
		List<String> tagsList = Arrays.asList(tagsArray);
		// spentOn
		Date today = new Date();
		Date parsedDate = DateUtil.parseDate(spentOnDate,
				DateUtil.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH);
		Date spentOn = parsedDate == null ? today : parsedDate;
		// category
		Long categoryId = categoryDAO.findIdByName(category);

		Expense newExpense = new Expense(amount, tagsList, spentOn, categoryId);
		newExpense.setId(id);

		// Get current expense
		Expense currentExpense = expenseDAO.findById(id);

		// Compare two expenses and replace stale data
		if (currentExpense.getAmount() != newExpense.getAmount()) {
			currentExpense.setAmount(newExpense.getAmount());
		}

		if (currentExpense.getSpentOn().getTime() != newExpense.getSpentOn()
				.getTime()) {
			currentExpense.setSpentOn(newExpense.getSpentOn());
		}

		if (currentExpense.getCategoryId() != newExpense.getCategoryId()) {
			currentExpense.setCategoryId(newExpense.getCategoryId());
		}

		// update the expense
		expenseDAO.update(currentExpense);

		// get tag ids linked to this expense
		List<Tag> tagObjects = expenseTagDAO
				.findsAllTagsByExpenseId(currentExpense.getId());

		Set<String> currentTagsSet = new HashSet<String>();

		for (Tag tagObject : tagObjects) {
			tagObject.setName(tagDAO.findNameById(tagObject.getId()));
			currentTagsSet.add(tagObject.getName());
		}

		Set<String> newTagsSet = new HashSet<String>(tagsList);

		// get tags to be added
		Set<String> tagsToBeAddedSet = new HashSet<String>();
		tagsToBeAddedSet.addAll(newTagsSet);
		tagsToBeAddedSet.removeAll(currentTagsSet);

		// add the tags to the expense
		for (String tagName : tagsToBeAddedSet) {
			Long tagId = tagDAO.findIdByName(tagName);
			if (tagId == null) {
				Tag tag = new Tag(tagName);
				tagId = tagDAO.insert(tag).getId();
			}
			expenseTagDAO.insert(currentExpense.getId(), tagId);
		}

		// get tags to be deleted
		Set<String> tagsToBeDeletedSet = new HashSet<String>();
		tagsToBeDeletedSet.addAll(currentTagsSet);
		tagsToBeDeletedSet.removeAll(newTagsSet);

		// delete the tags from expense
		for (String tagName : tagsToBeDeletedSet) {
			Long tagId = tagDAO.findIdByName(tagName);
			expenseTagDAO.delete(currentExpense.getId(), tagId);
		}

	}

	public List<Expense> getExpensesByDate(String onDate) {
		// Set the default date to be today
		Date date = new Date();
		if (StringUtils.isNotEmpty(onDate)) {
			date = DateUtil.parseDate(onDate,
					DateUtil.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH);
		}
		List<Expense> expenses = expenseDAO.findAllExpensesByDate(date);

		for (Expense expense : expenses) {
			List<Tag> tagsOfAnExpense = expenseTagDAO
					.findsAllTagsByExpenseId(expense.getId());
			logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			for (Tag tag : tagsOfAnExpense) {
				logger.info("Tags of an expense" + tag.getName());
			}
			logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			expense.extractTagNamesFromTagList(tagsOfAnExpense);
		}
		return expenses;
	}

	public double getTotalOfAllExpenses(List<Expense> expenses) {
		double total = 0.0;
		for (Expense expense : expenses) {
			total = total + expense.getAmount();
		}
		return total;
	}

}
