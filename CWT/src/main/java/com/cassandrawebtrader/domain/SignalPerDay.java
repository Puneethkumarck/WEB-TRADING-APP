package com.cassandrawebtrader.domain;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/**
 * @author puneethkumar
 * 
 * SignalPerDay domain object to map the signalperday table
 *
 */
@Table("signalperday")
public class SignalPerDay {
	
	@PrimaryKeyColumn(name = "signal_day", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
	private String signalDay;
	
	@PrimaryKeyColumn(name = "symbol", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
	private String symbol;
	
	@Column("signal_price")
	private Float price;
	
	@Column("signal_name")
	private String signalName;

	public String getSignalDay() {
		return signalDay;
	}

	public void setSignalDay(String signalDay) {
		this.signalDay = signalDay;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
		result = prime * result + ((signalDay == null) ? 0 : signalDay.hashCode());
		result = prime * result + ((signalName == null) ? 0 : signalName.hashCode());
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
		SignalPerDay other = (SignalPerDay) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (signalDay == null) {
			if (other.signalDay != null)
				return false;
		} else if (!signalDay.equals(other.signalDay))
			return false;
		if (signalName == null) {
			if (other.signalName != null)
				return false;
		} else if (!signalName.equals(other.signalName))
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
		return "SignalPerDay [signalDay=" + signalDay + ", symbol=" + symbol + ", price=" + price + ", signalName="
				+ signalName + "]";
	}

	public SignalPerDay(String signalDay, String symbol, Float price, String signalName) {
		super();
		this.signalDay = signalDay;
		this.symbol = symbol;
		this.price = price;
		this.signalName = signalName;
	}

	public SignalPerDay() {
		super();
		// TODO Auto-generated constructor stub
	}

}
