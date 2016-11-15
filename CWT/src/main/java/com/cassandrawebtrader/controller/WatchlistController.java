package com.cassandrawebtrader.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cassandrawebtrader.domain.Watchlist;
import com.cassandrawebtrader.dto.WatchlistForm;
import com.cassandrawebtrader.repository.WatchlistRepository;
import com.cassandrawebtrader.util.WatchlistMapper;

/**
 * @author puneethkumar
 * 
 * Controller to handle the watchlist Item
 *
 */
@Controller
@RequestMapping("/admin/watchlist")
public class WatchlistController {

	@Autowired
	private WatchlistRepository watchlistRepository;
	
	@Autowired
	private WatchlistMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showWatchlistPage(Model model) {
		List<Watchlist> watchlists = (List<Watchlist>) watchlistRepository.findAll();
		
		model.addAttribute("watchlists", watchlists);
		
		WatchlistForm watchlistForm = new WatchlistForm();
		
		model.addAttribute("watchlistForm", watchlistForm);
		
		return "watchlist";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String postWatchlistForm(@ModelAttribute("watchlistForm") @Valid WatchlistForm watchlistForm, BindingResult result) {
		if (result.hasErrors())
			return "watchlist";
		
		Watchlist watchlist = mapper.map(watchlistForm);
		
		watchlistRepository.save(watchlist);
		
		return "redirect:/admin/watchlist";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editWatchlistPage(@RequestParam(value = "symbol", required = true) String symbol) {
		ModelAndView modelAndView = new ModelAndView("watchlist-edit");
		
		Watchlist watchlist = watchlistRepository.findBySymbol(symbol);
		
		WatchlistForm watchlistForm = mapper.map(watchlist);
		modelAndView.addObject(watchlistForm);
		
		return modelAndView;		
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String postWatchlistPage(@ModelAttribute("watchlistForm") @Valid WatchlistForm watchlistForm, BindingResult result) {
		if (result.hasErrors())
			return "watchlist-edit";
		
		Watchlist watchlist = mapper.map(watchlistForm);
		
		watchlistRepository.save(watchlist);
		
		return "redirect:/admin/watchlist";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteWatchlistPage(@RequestParam(value = "symbol", required = true) String symbol) {
		Watchlist watchlist = new Watchlist();
		watchlist.setSymbol(symbol);
		
		watchlistRepository.delete(watchlist);
		
		return "redirect:/admin/watchlist";		
	}
	
}
