package com.cassandrawebtrader.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cassandrawebtrader.domain.Watchlist;
import com.cassandrawebtrader.repository.WatchlistRepository;
import com.cassandrawebtrader.service.ScannerService;
import com.cassandrawebtrader.service.YahooFeedService;

@Component
@EnableScheduling
@PropertySource("classpath:scheduler.properties")
public class YahooFeedJob {

	private static Logger logger = LoggerFactory.getLogger(YahooFeedJob.class);
	
	@Autowired
	private YahooFeedService yahooFeedService;
	
	@Autowired
	private WatchlistRepository watchlistRepository;
	
	@Autowired
	private Environment env;

	@Autowired
	private ScannerService scannerService;
	
	@Scheduled(fixedDelayString = "${com.cassandrawebtrader.fixedrate}")
	public void runYahooFeedService() throws InterruptedException {
		Boolean isRun = Boolean.valueOf(env.getProperty("com.cassandrawebtrader.runYahooFeedService").toUpperCase());
		
		if (isRun) {
			Integer sometime = Integer.valueOf(env.getProperty("com.cassandrawebtrader.sleep"));
			String watchlistcode = env.getProperty("com.cassandrawebtrader.watchlistcode");
			
			List<Watchlist> watchlists = (List<Watchlist>) watchlistRepository.findByWatchlistCode(watchlistcode);
			
			for (Watchlist watchlist: watchlists) {
				logger.info("Start getting " + watchlist.getSymbol() + " data.");
				yahooFeedService.getData(watchlist.getSymbol());
				logger.info("Finish getting " + watchlist.getSymbol() + " data.");
				logger.info("Start scanning " + watchlist.getSymbol() + " data.");
				scannerService.scan(watchlist.getSymbol());
				logger.info("Finish scanning " + watchlist.getSymbol() + " data.");
				Thread.sleep(sometime);
			}
		}
	}
	
}
