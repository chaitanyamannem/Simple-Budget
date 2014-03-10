package com.bus.sbud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bus.sbud.dao.jdbc.JDBCExpenseDAO;
import com.bus.sbud.service.AutoCompleteService;
import com.bus.sbud.util.AutoCompleteJson;

/**
 * This class is used to send json responses of autocomplete suggestions queried
 * by json response
 * 
 * @author chaitanyam
 * 
 */
@Controller
@RequestMapping(value = "/autocomplete", produces = "application/json")
public class AutoCompleteController {

	@Autowired
	AutoCompleteService autoCompleteService;

	@RequestMapping({ "/tags" })
	public @ResponseBody
	AutoCompleteJson getTags(@RequestParam("query") String query,
			Map<String, Object> model) {

		return autoCompleteService.getTagSuggestions(query);
	}

	@RequestMapping({ "/showpie" })
	public @ResponseBody
	Map<String, Double> showCategoryPie(Map<String, Object> model) {

		Map<String, Double> pieDataMap;
		/*
		 * try { pieDataMap = categoryDAO.findTotalByCategory(); return
		 * pieDataMap; } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */

		return null;
	}

	@RequestMapping({ "/showBar" })
	public @ResponseBody
	Map<String, Double> showDateBar(Map<String, Object> model) {
		JDBCExpenseDAO expenseDAO = new JDBCExpenseDAO();

		Map<String, Double> barDataMap;
		/*
		 * try {
		 * 
		 * barDataMap = expenseDAO.findTotalByDayInAMonth();
		 * 
		 * return barDataMap; } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

		return null;
	}
}
