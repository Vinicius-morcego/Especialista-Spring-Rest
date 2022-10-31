package com.algaworks.algafood.core.squiggly;



import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;

@Configuration
public class SquigglyConfig {
	
	@Bean
	public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper object){
		Squiggly.init(object, new RequestSquigglyContextProvider("campos", null));
		
		var filterRegistration = new FilterRegistrationBean<SquigglyRequestFilter>();		
		var urlPatterns = Arrays.asList("/pedidos/*", "/restaurantes/*");
		
		filterRegistration.setFilter(new SquigglyRequestFilter());
		filterRegistration.setOrder(1);		
		filterRegistration.setUrlPatterns(urlPatterns);
		return filterRegistration;
		
	}
}
