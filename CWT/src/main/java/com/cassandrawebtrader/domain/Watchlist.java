package com.cassandrawebtrader.domain;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/**
 * @author puneethkumar
 *
 *	Watchlist domain object to map the watchlist table
 */
@Table("watchlist")
public class Watchlist {
	
	@PrimaryKeyColumn(name = "watchlistcode", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
	private String watchlistcode;
	
	@PrimaryKeyColumn(name = "symbol", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
	private String symbol;
	
	@Column("active")
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
		Watchlist other = (Watchlist) obj;
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
		return "Watchlist [watchlistcode=" + watchlistcode + ", symbol=" + symbol + ", active=" + active + "]";
	}

	public Watchlist(String watchlistcode, String symbol, String active) {
		super();
		this.watchlistcode = watchlistcode;
		this.symbol = symbol;
		this.active = active;
	}

	public Watchlist() {
		super();
		this.watchlistcode = "HK001";
		this.active = "Y";
	}

}
