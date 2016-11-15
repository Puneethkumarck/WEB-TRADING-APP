package com.cassandrawebtrader.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassandrawebtrader.domain.Quote;
import com.cassandrawebtrader.repository.QuoteRepository;

/**
 * @author puneethkumar
 *
 */
@Service
public class YahooFeedService {
	
	private static Logger logger = LoggerFactory.getLogger(YahooFeedService.class);

	@Autowired
	private QuoteRepository quoteRepository;
	
	public static BufferedReader getStock(String symbol, int fromMonth,
			int fromDay, int fromYear, int toMonth, int toDay, int toYear) {

		try {
			// Retrieve CSV stream
			URL yahoo = new URL("http://ichart.yahoo.com/table.csv?s="
					+ symbol.toUpperCase() + "&a="
					+ Integer.toString(fromMonth) + "&b="
					+ Integer.toString(fromDay) + "&c="
					+ Integer.toString(fromYear) + "&d="
					+ Integer.toString(toMonth) + "&e="
					+ Integer.toString(toDay) + "&f="
					+ Integer.toString(toYear) + "&g=d&ignore=.csv");
			logger.info(yahoo.toString());
			URLConnection connection = yahoo.openConnection();
			InputStreamReader is = new InputStreamReader(
					connection.getInputStream());
			// return the BufferedReader
			return new BufferedReader(is);

		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		return null;
	}
	
	public static Quote parseQuote(String symbol, String[] feed) throws java.text.ParseException {
		Date date = null;
		float low = 0;
		float high = 0;
		float open = 0;
		float close = 0;
		double volume = 0;

		date = new SimpleDateFormat("yyyy-MM-dd").parse(feed[0]);
		open = Float.parseFloat(feed[1]);
		high = Float.parseFloat(feed[2]);
		low = Float.parseFloat(feed[3]);
		close = Float.parseFloat(feed[4]);
		volume = Double.parseDouble(feed[5]);

		// create a Quote POJO
		return new Quote(symbol.toUpperCase(), date, open, high, low, close,
				volume);
	}
	
	public void getData(final String symbol) {
		final Calendar cal = Calendar.getInstance();

		// today is the default end date for fetching stock quote data
		final Date today = new Date();
		cal.setTime(today);
		final int toDay = cal.get(Calendar.DAY_OF_MONTH);
		final int toMonth = cal.get(Calendar.MONTH);
		final int toYear = cal.get(Calendar.YEAR);

		// get the last date of stock quote data of a stock
		// if none is found, use the default 1-JAN-2000 as the start date
		// otherwise, use the next day of the last date in Cassandra
		Quote lastQuote = quoteRepository.findLastBySymbol(symbol);
		Date lastQuoteDate = null;
		if (lastQuote != null)
			lastQuoteDate = lastQuote.getDate();
		int fromDay = 1;
		int fromMonth = 0;
		int fromYear = 2000;
		if (lastQuoteDate != null) {
			cal.setTime(lastQuoteDate);
			cal.add(Calendar.DATE, 1);
			fromDay = cal.get(Calendar.DAY_OF_MONTH);
			fromMonth = cal.get(Calendar.MONTH);
			fromYear = cal.get(Calendar.YEAR);
		}

		// retrieve stock quote data from Yahoo! Finance
		final BufferedReader br = getStock(symbol, fromMonth, fromDay,
				fromYear, toMonth, toDay, toYear);

		if (br != null) {
			try {
				// process each line of stock quote data
				for (String line = br.readLine(); line != null; line = br
						.readLine()) {
					String[] feed = line.split(",");
					// skip the header line
					if (!Pattern.matches("Date", feed[0])) {
						// extract each line into the Quote POJO
						Quote q = parseQuote(symbol, feed);
						// persist the Quote POJO into Cassandra
						quoteRepository.save(q);
					}
				}
				logger.info(symbol + " is saved.");
			} catch (IOException e) {
				logger.error(e.toString(), e);
			} catch (ParseException e) {
				logger.error(e.toString(), e);
			}
		}
	}
	
}
