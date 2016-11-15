package com.cassandrawebtrader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cassandrawebtrader.dto.Chart;

/**
 * @author puneethkumar
 * 
 * Controller class to handle the Chart
 *
 */
@Controller
public class ChartController {
	
	private static Logger logger = LoggerFactory.getLogger(ChartController.class);
	
	@RequestMapping(value = "/member/chart", method = RequestMethod.GET)
	public String showChartPage(Model model) {
		Chart chart = new Chart();
		model.addAttribute("chart", chart);
		
		return "cloud";
	}

	@RequestMapping(value = "/member/chart", method = RequestMethod.POST)
	public String processChartPage(@ModelAttribute("chart") Chart chart, Model model) {
		model.addAttribute("chart", chart);
		
		return "cloudchart";
	}

}
