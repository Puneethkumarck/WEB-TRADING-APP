package com.cassandrawebtrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cassandrawebtrader.domain.SignalHistory;
import com.cassandrawebtrader.dto.Chart;
import com.cassandrawebtrader.repository.SignalHistoryRepository;

/**
 * @author puneethkumar
 * 
 * Controller Class to handle the SignalHistory
 *
 */
@Controller
@RequestMapping("/member/signalhistory")
public class SignalHistoryController {
	
	@Autowired
	private SignalHistoryRepository signalHistoryRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showSignalHistory(Model model) {
		Chart chart = new Chart();
		model.addAttribute("chart", chart);
		
		return "signalhistory";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSignalHistory(@ModelAttribute("chart") Chart chart, Model model) {
		List<SignalHistory> signalhistory = signalHistoryRepository.findBySymbol(chart.getSymbol());
		
		model.addAttribute("signalhistory", signalhistory);
		
		return "signalhistory";
	}

}
