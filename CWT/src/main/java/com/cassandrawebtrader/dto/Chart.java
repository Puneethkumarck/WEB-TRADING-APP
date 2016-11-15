package com.cassandrawebtrader.dto;

/**
 * @author puneethkumar
 *
 */
public class Chart {
	
	private String symbol;

	/**
	 * @return
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @param symbol
	 */
	public Chart(String symbol) {
		super();
		this.symbol = symbol;
	}

	/**
	 * 
	 */
	public Chart() {
		super();
		// TODO Auto-generated constructor stub
	}

}
