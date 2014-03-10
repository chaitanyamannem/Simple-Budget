package com.bus.sbud.controllers;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bus.sbud.dao.jdbc.JDBCExpenseDAO;
import com.bus.sbud.model.Expense;
import com.bus.sbud.service.CategoryService;
import com.bus.sbud.service.ExpenseService;

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

	@Autowired
	JDBCExpenseDAO expenseDAO;
	@Autowired
	ExpenseService expenseService;
	@Autowired
	CategoryService categoryService;

	@RequestMapping({ "/showAddExpensePage" })
	public String showHomePage(Map<String, Object> model) {
		// TODO do we need this for just showing a page or is there a better way
		return "addExpensePage";
	}

	@RequestMapping({ "/getExpenseData" })
	public String saveExpense(@RequestParam("amount") double amount,
			@RequestParam("hidden-tags") String hiddenTags,
			@RequestParam("date") String date,
			@RequestParam("category") String category, Map<String, Object> model) {
		expenseService.addAnExpense(amount, hiddenTags, date, category);
		return "homePage";
	}

	@RequestMapping({ "/edit" })
	public String editExpense(@RequestParam("expenseId") long id,
			@RequestParam("amount") double amount,
			@RequestParam("spentOn") String spentOn,
			@RequestParam("hidden-tags") String hiddenTags,
			@RequestParam("category") String category, Map<String, Object> model) {
		expenseService.editAnExpense(id, amount, hiddenTags, spentOn, category);
		return "redirect:showByDate?date=" + spentOn;

	}

	@RequestMapping({ "/showByDate" })
	public String showExpensesOnDate(@RequestParam("date") String onDate,
			Map<String, Object> model) {
		List<Expense> expenses = expenseService.getExpensesByDate(onDate);
		for(Expense expense : expenses){
			expense.setCategoryName(categoryService.getCategoryName(expense.getCategoryId()));
		}
		logger.info("No of expenses = " + expenses.size());
		model.put("expenses", expenses);
		model.put("total", expenseService.getTotalOfAllExpenses(expenses));

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
