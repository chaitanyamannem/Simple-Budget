package com.bus.sbud.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bus.sbud.dao.CategoryDAO;
import com.bus.sbud.dao.TagDAO;
import com.bus.sbud.util.AutoCompleteJson;
import com.bus.sbud.util.PieJSON;

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
	private static final Logger logger = Logger
			.getLogger("AutoCompleteController");

	@RequestMapping({ "/tags" })
	public @ResponseBody
	AutoCompleteJson getTags(@RequestParam("query") String iQuery,
			Map<String, Object> model) {
		logger.info("########Entered AutoCompleteController ##########");
		TagDAO tagDAO = new TagDAO();
		List<String> suggestions = new ArrayList<String>();
		List<Long> data = new ArrayList<Long>();
		try {
			Map<String,Long> tagMap = tagDAO.findTagNameAndIDsBySearchString(iQuery);
			for(Map.Entry<String, Long> tagRecord :tagMap.entrySet()){
				suggestions.add(tagRecord.getKey());
				data.add(tagRecord.getValue());
				logger.info("Tag Map -> " + tagRecord.getKey() + "=" +tagRecord.getValue());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = iQuery;
//		String[] suggestions = { "Apple", "Apricot", "Angel", "Adhurs",
//				"Anchor" };
//		long[] data = { 1L, 2L, 3L, 4L, 5L };
		return new AutoCompleteJson(query, suggestions, data);
	}
	
	@RequestMapping({ "/showpie" })
	public @ResponseBody
	Map<String, Double> showCategoryPie(Map<String, Object> model) {
		CategoryDAO categoryDAO = new CategoryDAO();
		
		
		Map<String, Double> pieDataMap;
		try {
			 pieDataMap = categoryDAO.findTotalByCategory();
			 //return new PieJSON(pieDataMap);
			 return pieDataMap;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
