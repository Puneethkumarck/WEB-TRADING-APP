package com.cassandrawebtrader.dto;

/**
 * @author puneethkumar
 *
 */
public class Ohlc {

	private final String date;
	
	private final String open;
	
	private final String high;
	
	private final String low;
	
	private final String close;
	
	private final String volume;

	/**
	 * @return
	 */
	public String getDate() {
		return date;
	}

	public String getOpen() {
		return open;
	}

	public String getHigh() {
		return high;
	}

	public String getLow() {
		return low;
	}

	public String getClose() {
		return close;
	}

	public String getVolume() {
		return volume;
	}

	@Override
	public String toString() {
		return "Ohlc [date=" + date + ", open=" + open + ", high=" + high
				+ ", low=" + low + ", close=" + close + ", volume=" + volume
				+ "]";
	}

	/**
	 * @param date
	 * @param open
	 * @param high
	 * @param low
	 * @param close
	 * @param volume
	 */
	public Ohlc(String date, String open, String high, String low,
			String close, String volume) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}	
	
}
