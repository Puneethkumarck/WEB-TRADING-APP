package com.cassandrawebtrader.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cassandrawebtrader.dto.Username;
import com.cassandrawebtrader.service.MemberService;

/**
 * @author puneethkumar
 * 
 * Controller Class to handle the forgotPassword
 *
 */
@Controller
public class ForgotPasswordController {
	
	private static Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String showForgotPasswordPage(Model model) {
		Username username = new Username();
		model.addAttribute("username", username);
		
		return "forgotpassword";		
	}
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String processForgotPasswordForm(@ModelAttribute("username") @Valid Username username, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return "forgotpassword";
		
		if (!memberService.generateForgotPasswordBody(username.getUsername()))
			return "forgotpassword";
		
		redirectAttributes.addFlashAttribute("flashType", "success");
		redirectAttributes.addFlashAttribute("flashMessage", "Forgot Password Email is sent, please check.");
		
		return "redirect:/home";
	}

}
