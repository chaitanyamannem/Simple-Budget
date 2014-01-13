package com.bus.sbud.controllers;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bus.sbud.util.AutoCompleteJson;

/**
 * This class is used to send json responses of autocomplete suggestions queried
 * by json response
 * 
 * @author chaitanyam
 * 
 */
@Controller
@RequestMapping(value = "/autocomplete",produces = "application/json")
public class AutoCompleteController {
	private static final Logger logger = Logger
			.getLogger("AutoCompleteController");

	@RequestMapping({ "/tags" })
	public @ResponseBody AutoCompleteJson getTags(Map<String, Object> model) {
		logger.info("########Entered AutoCompleteController ##########");
		String query = "li";
		String[] suggestions = { "Apple", "Apricot", "Angel", "Adhurs",
				"Anchor" };
		long[] data = { 1L, 2L, 3L, 4L, 5L };
		return new AutoCompleteJson(query, suggestions, data);
	}
}
