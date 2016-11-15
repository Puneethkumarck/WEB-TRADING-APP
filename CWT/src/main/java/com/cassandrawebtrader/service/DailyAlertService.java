package com.cassandrawebtrader.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.cassandrawebtrader.domain.Member;
import com.cassandrawebtrader.domain.SignalPerDay;
import com.cassandrawebtrader.repository.MemberRepository;
import com.cassandrawebtrader.repository.SignalPerDayRepository;
import com.cassandrawebtrader.util.CommonUtil;

/**
 * @author puneethkumar
 *
 */
@Service
public class DailyAlertService {
	
	private static Logger logger = LoggerFactory.getLogger(DailyAlertService.class);
	
	@Autowired
	private SignalPerDayRepository signalPerDayRepository;
	
	@Autowired
	private MemberRepository memberRepository;

	@Resource(name = "notifyChannel")
	private MessageChannel notifyChannel;
	
	public void generateTradingSignalAlert() {
		final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		// final String today = df.format(new Date());
		final String today = "20150128";
		
		List<Member> members = memberRepository.findByActive();
		
		for (Member m: members)
			generateAlertBody(m.getEmail(), today);
	}

	private void generateAlertBody(String email, String day) {
		List<SignalPerDay> signalPerDay = signalPerDayRepository.findByDate(day);
		
		StringBuilder line = new StringBuilder();
		
		line.append(email + "|");
		line.append("Date: " + day + "\n\n");
		
		if (signalPerDay.size() > 0) {
			for(SignalPerDay signal: signalPerDay) {
				line.append(signal.getSymbol());
				line.append("\t");
				line.append(CommonUtil.convertDecimal(signal.getPrice()));
				line.append("\t");
				line.append(signal.getSignalName());
				line.append("\n");
			}
		} else {
			line.append("Today has no trading signals.\n");			
		}
		
		Message<String> message = MessageBuilder.withPayload(line.toString()).build();
		
		logger.info(line.toString());
		logger.info(message.toString());
		logger.info(notifyChannel.toString());
		
		notifyChannel.send(message);
	}

}
