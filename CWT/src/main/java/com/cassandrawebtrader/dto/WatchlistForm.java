package com.cassandrawebtrader.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author puneethkumar
 *
 */
public class WatchlistForm {
	
	@NotEmpty
	@Size(min = 1, max = 20)
	private String watchlistcode;
	
	@NotEmpty
	@Size(min = 1, max = 20)
	private String symbol;
	
	@NotEmpty
	@Size(min = 1, max = 1)
	private String active;

	public String getWatchlistcode() {
		return watchlistcode;
	}

	public void setWatchlistcode(String watchlistcode) {
		this.watchlistcode = watchlistcode;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((watchlistcode == null) ? 0 : watchlistcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WatchlistForm other = (WatchlistForm) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (watchlistcode == null) {
			if (other.watchlistcode != null)
				return false;
		} else if (!watchlistcode.equals(other.watchlistcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WatchlistForm [watchlistcode=" + watchlistcode + ", symbol=" + symbol + ", active=" + active + "]";
	}

	public WatchlistForm(String watchlistcode, String symbol, String active) {
		super();
		this.watchlistcode = watchlistcode;
		this.symbol = symbol;
		this.active = active;
	}

	public WatchlistForm() {
		super();
		this.watchlistcode = "HK001";
		this.active = "Y";
	}	

}
