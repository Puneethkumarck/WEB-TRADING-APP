package com.cassandrawebtrader.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author puneethkumar
 * 
 * Controller Class to route the Control to the Home Page
 *
 */
@Configuration
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping
	public String showHomePage() {
		return "home";
	}

}
