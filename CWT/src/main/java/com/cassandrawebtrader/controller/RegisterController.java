package com.cassandrawebtrader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cassandrawebtrader.dto.RegisterForm;
import com.cassandrawebtrader.service.MemberService;

/**
 * @author puneethkumar
 * 
 * Controller Class to handle the Customer Registration
 *
 */
@Configuration
@RequestMapping("/member")
public class RegisterController {
	
	private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		RegisterForm registerForm = new RegisterForm();
		model.addAttribute("registerForm", registerForm);
		logger.info(registerForm.toString());
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegisterForm(@ModelAttribute("registerForm") RegisterForm registerForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return "register";
		logger.info(registerForm.toString());
		memberService.registerNewMember(registerForm);
		
		redirectAttributes.addFlashAttribute("flashType", "success");
		redirectAttributes.addFlashAttribute("flashMessage", "Registration successful.");
		
		return "redirect:/home";
	}

}
