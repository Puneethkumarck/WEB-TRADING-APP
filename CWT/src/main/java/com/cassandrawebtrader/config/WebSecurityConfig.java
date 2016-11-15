package com.cassandrawebtrader.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author puneethkumar
 * 
 * Config class to handle the Spring Security
 *
 */
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Resource
	private UserDetailsService userService;
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/home",
							"/error",
							"/member/register",
							"/forgotpassword",
							"/mappings",
							"/configprops",
							"/configprops",
							"/trace",
							"/info",
							"/mappings",
							"/health",
							"/dump",
							"/env",
							"/metrics",
							"/css/**",
							"/js/**",
							"/fonts/**",
							"**.html",
							"/static/**",
							"/public/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest()
						.authenticated();
		http
			.formLogin()
			.loginPage("/login")
				.permitAll().and()
			.logout()
				.permitAll();
	}

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.userDetailsService(userService);
	}
	
}
