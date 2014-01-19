package com.bus.sbud.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bus.sbud.dao.CategoryDAO;
import com.bus.sbud.dao.ExpenseDAO;
import com.bus.sbud.dao.TagDAO;
import com.bus.sbud.model.Expense;
import com.bus.sbud.model.Tag;
import com.bus.sbud.util.DateUtil;

/**
 * This class deals with all expense related stuff.
 * 
 * @author devchaitu18
 * 
 */
@Controller
@RequestMapping(value = "/expenses")
public class ExpenseController {

	private static final Logger logger = Logger.getLogger("ExpenseController");

	@RequestMapping({ "/add" })
	public String showHomePage(Map<String, Object> model) {
		logger.info("########Entered expense controller ##########");
		model.put("welcome", "Welcome to the joy of money management");

		return "addExpensePage";
	}

	@RequestMapping({ "/getExpenseData" })
	public String saveExpense(@RequestParam("amount") double amount,
			@RequestParam("hidden-tags") String hiddenTags,
			@RequestParam("date") String date,
			@RequestParam("category") String category, Map<String, Object> model) {
		logger.info("########Entered expense controller - /getExpenseData ##########");
		logger.info(hiddenTags);
		logger.info(amount + "");

		String[] tags = StringUtils.split(hiddenTags, ',');
		ExpenseDAO expenseDao = new ExpenseDAO();
		TagDAO tagDao = new TagDAO();
		CategoryDAO categoryDAO = new CategoryDAO();
		Expense expense = new Expense();
		expense.setAmount(amount);

		Date today = new Date();
		Date parsedDate = DateUtil.parseDate(date,
				DateUtil.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH);
		Date spentOn = parsedDate == null ? today : parsedDate;
		expense.setWhenCreated(spentOn);
		expense.setTlm(spentOn);

		try {
			expense.setCategoryId(categoryDAO.findIdByName(category));
			expense.setId(expenseDao.save(expense));
			logger.info(expense.getCategoryId() + "*****");
			for (String tagName : tags) {
				long tagId = tagDao.findIdByName(tagName);
				if (tagId == -1) {
					Tag tag = new Tag(tagName);
					tagId = tagDao.save(tag);
				}

				tagDao.linkTagNExpense(expense.getId(), tagId);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "homePage";
	}

	@RequestMapping({ "/edit" })
	public String editExpense(@RequestParam("expenseId") long id,
			@RequestParam("amount") double amount,
			@RequestParam("hidden-tags") String hiddenTags,
			@RequestParam("category") String category, Map<String, Object> model) {
		logger.info("##################################################################################################################");
		logger.info("Class - ExpenseController");
		logger.info("Method - editExpense - start");
		logger.info("expenseId = " + id);
		logger.info("Edit tags input = " + hiddenTags);
		logger.info("Amount = " + amount);
		logger.info("category = " + category);
		logger.info("##################################################################################################################");

		String[] tags = StringUtils.split(hiddenTags, ',');
		String showDate = "";
		ExpenseDAO expenseDAO = new ExpenseDAO();
		TagDAO tagDao = new TagDAO();
		CategoryDAO categoryDAO = new CategoryDAO();
		Expense newExpense = new Expense();
		newExpense.setAmount(amount);
		// tlm
		Date today = new Date();
		Date lastModified = today;

		try {
			// id
			Expense currentExpense = expenseDAO.findById(id);
			// amount
			if (newExpense.getAmount() != currentExpense.getAmount()) {
				currentExpense.setAmount(newExpense.getAmount());
			}
			// category id
			Long newCategoryId = categoryDAO.findIdByName(category);
			if (newCategoryId != null
					&& !newCategoryId.equals(currentExpense.getCategoryId())) {
				currentExpense.setCategoryId(newCategoryId);
			}
			// always set tlm
			currentExpense.setTlm(lastModified);
			expenseDAO.update(currentExpense);
			logger.info("Tags to edit" + tags.toString());
			for (String tagName : tags) {
				long tagId = tagDao.findIdByName(tagName);
				logger.info("Tag name " + tagName);
				logger.info("Tag id " + tagId);
				if (tagId == -1) {
					Tag tag = new Tag(tagName);
					tagId = tagDao.save(tag);
				}
				boolean isTagLinked = tagDao.isTagLinkedToExpense(tagId,
						currentExpense.getId());
				logger.info("isTagLinked = " + isTagLinked);
				if (!isTagLinked) {
					tagDao.linkTagNExpense(currentExpense.getId(), tagId);
				}

			}
			// form the date for the url
			Date whenCreated = currentExpense.getWhenCreated();
			showDate = DateUtil.formatDate(whenCreated,
					DateUtil.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("********************************************End**********************************************************************");
		return "redirect:showByDate?date=" + showDate;
	}

	@RequestMapping({ "/showByDate" })
	public String showExpensesOnDate(@RequestParam("date") String onDate,
			Map<String, Object> model) {
		double total = 0.0;
		ExpenseDAO expenseDao = new ExpenseDAO();
		TagDAO tagDao = new TagDAO();
		// onDate = "2014-01-08";
		Date date = DateUtil.parseDate(onDate,
				DateUtil.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH);
		try {
			List<Long> expenseids = expenseDao
					.findExpensesByDate(date == null ? new Date() : date);
			List<Expense> expenses = new ArrayList<Expense>();
			for (Long id : expenseids) {
				Expense expense = expenseDao.findById(id);
				List<Long> tagids = tagDao.findTagsByExpense(id);
				expense.setTags(tagDao.findTagNamesByIds(tagids));
				expenses.add(expense);
				total = total + expense.getAmount();
			}
			model.put("expenses", expenses);
			model.put("total", total);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "showExpensesPage";
	}

	@RequestMapping({ "/showcat" })
	public String showExpensesOnDate(Map<String, Object> model) {
		return "showCat";
	}
	
	@RequestMapping({ "/showBar" })
	public String showGraphByDateInAMonth(Map<String, Object> model) {
		return "showBarPage";
	}

}
