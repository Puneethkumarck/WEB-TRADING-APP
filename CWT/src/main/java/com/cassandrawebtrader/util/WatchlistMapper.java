package com.cassandrawebtrader.util;

import org.springframework.stereotype.Component;

import com.cassandrawebtrader.domain.Watchlist;
import com.cassandrawebtrader.dto.WatchlistForm;

/**
 * @author puneethkumar
 *
 * Util Calss to Map the WatchList Items
 */
@Component
public class WatchlistMapper {

	public Watchlist map(WatchlistForm model) {
		Watchlist domain = new Watchlist();
		
		domain.setWatchlistcode(model.getWatchlistcode());
		domain.setSymbol(model.getSymbol());
		domain.setActive(model.getActive());
		
		return domain;
	}
	
	public WatchlistForm map(Watchlist domain) {
		WatchlistForm model = new WatchlistForm();
		
		model.setWatchlistcode(domain.getWatchlistcode());
		model.setSymbol(domain.getSymbol());
		model.setActive(domain.getActive());
		
		return model;
	}
	
}
