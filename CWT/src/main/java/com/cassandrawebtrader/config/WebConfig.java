package com.cassandrawebtrader.config;
import java.util.Locale;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author puneethkumar
 * 
 * Config Class to handle the WebConfig
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	/**
	 *  This Method used to configure the JSP pages based on Http Status
	 *  
	 * @return container ConfigurableEmbeddedServletContainer
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		
		return new EmbeddedServletContainerCustomizer() {
			
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				// TODO Auto-generated method stub
				
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
				
				container.addErrorPages(error401Page, error404Page, error500Page);
			}
		};
	}
	
	
	/*  @Bean
	    public EmbeddedServletContainerFactory servletContainer() {
	        TomcatEmbeddedServletContainerFactory factory = 
	                      new TomcatEmbeddedServletContainerFactory();
	        return factory;
	     }*/
	  
	  
	/**
	 * This method is used to to create the property file instance via ResourceBundle
	 * 
	 * @return messageSource MessageSource
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		
		return messageSource;		
	}
	
	/**
	 *  This method is to resolve session Locale
	 *  
	 * @return resolver LocaleResolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		
		return resolver;
	}
	
	/**
	 * This method is used to register the interceptors used in application
	 *  
	 * @param registry InterceptorRegistry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("language");
		registry.addInterceptor(changeInterceptor);
	}
	
	
	/**
	 * This method is used to set the view Controllers which will be invoked directly.
	 *  
	 * @param registry ViewControllerRegistry
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login").setViewName("login");
	}
	
	

}
