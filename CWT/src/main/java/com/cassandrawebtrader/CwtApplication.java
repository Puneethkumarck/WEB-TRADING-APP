package com.cassandrawebtrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author puneethkumar
 *
 */
@SpringBootApplication
public class CwtApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CwtApplication.class);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(CwtApplication.class, args);
    }
}
