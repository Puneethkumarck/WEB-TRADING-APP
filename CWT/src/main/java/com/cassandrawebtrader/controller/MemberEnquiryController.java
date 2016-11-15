package com.cassandrawebtrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cassandrawebtrader.domain.Member;
import com.cassandrawebtrader.repository.MemberRepository;

/**
 * @author puneethkumar
 * 
 * Controller Class to handle the MemberRepo
 *
 */
@Controller
@RequestMapping("/admin/memberenquiry")
public class MemberEnquiryController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@RequestMapping
	public String showMemberEnquiryPage(Model model) {
		List<Member> members = (List<Member>) memberRepository.findAll();
		
		model.addAttribute("members", members);
		
		return "memberenquiry";
	}

}
