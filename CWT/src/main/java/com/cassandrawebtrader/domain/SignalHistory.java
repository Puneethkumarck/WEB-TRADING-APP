package com.cassandrawebtrader.domain;

import java.util.Date;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/**
 * @author puneethkumar
 * 
 * SignalHistory domain object to map the signalhistory table
 *
 */
@Table("signalhistory")
public class SignalHistory {
	
	@PrimaryKeyColumn(name = "symbol", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
	private String symbol;
	
	@PrimaryKeyColumn(name = "signal_time", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
	private Date signalTime;
	
	@Column("signal_price")
	private Float price;
	
	@Column("signal_name")
	private String signalName;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getSignalTime() {
		return signalTime;
	}

	public void setSignalTime(Date signalTime) {
		this.signalTime = signalTime;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getSignalName() {
		return signalName;
	}

	public void setSignalName(String signalName) {
		this.signalName = signalName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((signalName == null) ? 0 : signalName.hashCode());
		result = prime * result + ((signalTime == null) ? 0 : signalTime.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		SignalHistory other = (SignalHistory) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (signalName == null) {
			if (other.signalName != null)
				return false;
		} else if (!signalName.equals(other.signalName))
			return false;
		if (signalTime == null) {
			if (other.signalTime != null)
				return false;
		} else if (!signalTime.equals(other.signalTime))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SignalHistory [symbol=" + symbol + ", signalTime=" + signalTime + ", price=" + price + ", signalName="
				+ signalName + "]";
	}

	public SignalHistory(String symbol, Date signalTime, Float price, String signalName) {
		super();
		this.symbol = symbol;
		this.signalTime = signalTime;
		this.price = price;
		this.signalName = signalName;
	}

	public SignalHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
