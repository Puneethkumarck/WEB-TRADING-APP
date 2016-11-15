package com.cassandrawebtrader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

/**
 * @author puneethkumar
 * 
 * Config class for Spring Intergration
 *
 */
@Configuration
@IntegrationComponentScan("com.cassandrawebtrader")
@EnableIntegration
public class IntegrationConfig {
	
	@Bean(name = "notifyChannel")
	public MessageChannel emailChannel() {
		
		//A channel that invokes a single subscriber for each sent Message. The invocation will occur in the sender's thread.
		return new DirectChannel();
	}

}
