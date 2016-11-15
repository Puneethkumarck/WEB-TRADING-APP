package com.cassandrawebtrader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @author puneethkumar
 *
 */
@Service
@PropertySource("classpath:mail.properties")
public class MailService {
	
	private static Logger logger = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private Environment env;
	
	@ServiceActivator(inputChannel = "notifyChannel")
	public void sendMailAlert(String body) {
		String parts[] = body.split("\\|");
		String email = parts[0];
		String realbody = parts[1];		
		
		logger.info("MailService : " + body);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setFrom(env.getProperty("com.cassandrawebtrader.mail.from"));
		msg.setSubject(env.getProperty("com.cassandrawebtrader.mail.subject"));
		msg.setText(realbody);
		try {
			mailSender.send(msg);
			logger.info(msg.toString());
		} catch (MailException e) {
			logger.error(e.getMessage());
		}
	}

	public void sendGenericMail(String email, String subject, String body) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setFrom(env.getProperty("com.cassandrawebtrader.mail.from"));
		msg.setSubject(subject);
		msg.setText(body);
		try {
			mailSender.send(msg);
			logger.info(msg.toString());
		} catch (MailException e) {
			logger.error(e.getMessage());
		}
	}

}
