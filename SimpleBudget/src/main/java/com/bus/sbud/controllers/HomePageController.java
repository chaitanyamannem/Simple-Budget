/**
 * 
 */
package com.bus.sbud.controllers;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller deals with the logic of website's home page.
 * @author chaitanyam
 *
 */

@Controller
public class HomePageController {
	
	private static final Logger logger = Logger.getLogger("HomePageController");
	
	@RequestMapping({"/welcome"})
	public String showHomePage(Map<String, Object> model){
		logger.info("########Entered homepage controller ##########");
		model.put("welcome", "Welcome to the joy of money management");
		
		return "homePage";
	}
	
	@RequestMapping({"/linkDropBox"})
	public String linkDropBox(Map<String, Object> model){
		logger.info("########/linkDropBox##########");
		
		
		return "linkDropBoxPage";
	}

}
