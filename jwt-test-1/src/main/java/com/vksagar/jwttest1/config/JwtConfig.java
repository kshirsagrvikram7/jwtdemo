package com.vksagar.jwttest1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vksagar.jwttest1.handler.JWTFilter;

@Configuration
public class JwtConfig {

	@Autowired
	private JWTFilter jwtFilter;
	
	@Bean
	public FilterRegistrationBean<JWTFilter> filterRegistrationBean() {
		System.out.println("In filterRegistrationBean --->");
		FilterRegistrationBean<JWTFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(jwtFilter);
		bean.addUrlPatterns("/secured/*");
		return bean;
	}
}
