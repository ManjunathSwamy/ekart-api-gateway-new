package com.harness.explorer.apigateway.config;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.apache.HttpClientRibbonCommandFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;

@ComponentScan (basePackages ={"com.vmware.platform.apigateway"}, excludeFilters = @Filter({Controller.class}))
public class ApplicationConfig {
	
	@Autowired(required = false)
	private Set<ZuulFallbackProvider> zuulFallbackProviders = Collections.emptySet();
	
	 @Bean
	    public RibbonCommandFactory<?> ribbonCommandFactory(
	            final SpringClientFactory clientFactory, ZuulProperties zuulProperties) {
		 
		 	return new HttpClientRibbonCommandFactory(clientFactory, zuulProperties, zuulFallbackProviders);
	        //return new HttpClientRibbonCommandFactory(clientFactory);
	    }
	
}
