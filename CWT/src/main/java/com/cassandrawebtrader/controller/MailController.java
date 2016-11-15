package com.cassandrawebtrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cassandrawebtrader.service.MailService;

/**
 * @author puneethkumar
 * 
 * 
 *
 */
@Controller
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping("/mail")
	public String sendMail() {
		mailService.sendMailAlert("Testing message");
		
		return "redirect:/home";
	}

}
