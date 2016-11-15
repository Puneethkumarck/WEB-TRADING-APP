package com.cassandrawebtrader.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassandrawebtrader.domain.Quote;
import com.cassandrawebtrader.domain.SignalHistory;
import com.cassandrawebtrader.domain.SignalPerDay;
import com.cassandrawebtrader.repository.QuoteRepository;
import com.cassandrawebtrader.repository.SignalHistoryRepository;
import com.cassandrawebtrader.repository.SignalPerDayRepository;
import com.cassandrawebtrader.util.CommonUtil;
import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

/**
 * @author puneethkumar
 *
 */
@Service
public class ScannerService {
	
	private static Logger logger = LoggerFactory.getLogger(ScannerService.class);
	
	private final int DAYS_TO_SCAN = 1000;
	
	private Core taLibCore;
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	@Autowired
	private SignalHistoryRepository signalHistoryRepository;
	
	@Autowired
	private SignalPerDayRepository signalPerDayRepository;
	
	public ScannerService() {
		this.taLibCore = new Core();
	}
	
	public void scan(String symbol) {
		List<Quote> quotes = quoteRepository.findLastNBySymbol(symbol, DAYS_TO_SCAN);
		bullishEngulfing(quotes);
	}
	
	public void bullishEngulfing(final List<Quote> quotes) {
		final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		final SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
		
		final String SIGNAL_NAME = "Bullish Engulfing";
		final int SMA_PERIOD = 12;
		
		double close[] = new double[quotes.size()];
		double sma[] = new double[quotes.size()];
		
		Collections.reverse(quotes);
		for (int i = 0; i < quotes.size(); i++)
			close[i] = quotes.get(i).getClose();
		
		MInteger beginIdx = new MInteger();
		MInteger lengthIdx = new MInteger();
		
		RetCode retCode = taLibCore.sma(0, close.length-1, close, SMA_PERIOD, beginIdx, lengthIdx, sma);
		
		if (retCode == RetCode.Success) {
			for(int i = beginIdx.value+1; i < quotes.size(); i++) {
				Quote lastQuote = quotes.get(i-1);
				Quote thisQuote = quotes.get(i);

				if (
					(thisQuote.getHigh() > lastQuote.getHigh()) &&
					(thisQuote.getLow() < lastQuote.getLow()) &&
					(thisQuote.getClose() > lastQuote.getClose()) &&
					(thisQuote.getOpen() < lastQuote.getOpen()) &&
					(thisQuote.getClose() > thisQuote.getOpen()) &&
					(lastQuote.getClose() < lastQuote.getOpen()) &&
					(sma[i-1] < sma[i])) {
					
						SignalHistory signalHistory = new SignalHistory(thisQuote.getSymbol(), thisQuote.getDate(), thisQuote.getClose(), SIGNAL_NAME);
						signalHistoryRepository.save(signalHistory);
						
						SignalPerDay signalPerDay = new SignalPerDay(df2.format(thisQuote.getDate()), thisQuote.getSymbol(), thisQuote.getClose(), SIGNAL_NAME);
						signalPerDayRepository.save(signalPerDay);
					
						StringBuilder line = new StringBuilder();
						line.append("Period #");
						line.append(i + 1);
						line.append(" Signal=bullishEngulfing");
						line.append(" Date=");
						line.append(df.format(thisQuote.getDate()));
						line.append(" prevClose=");
						line.append(CommonUtil.convertDecimal(lastQuote.getClose()));
						line.append(" prevSMA(12)=");
						line.append(CommonUtil.convertDecimal(sma[i-1]));
						line.append(" close=");
						line.append(CommonUtil.convertDecimal(thisQuote.getClose()));
						line.append(" SMA(12)=");
						line.append(CommonUtil.convertDecimal(sma[i]));
						logger.info(line.toString());
					}
			}
		}
	}

}
