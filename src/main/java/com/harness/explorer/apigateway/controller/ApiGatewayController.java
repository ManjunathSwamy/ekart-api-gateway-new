package com.harness.explorer.apigateway.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.harness.explorer.apigateway.vos.StatusVO;

@RestController
@RequestMapping ("/api/v1")
public class ApiGatewayController {

	private final Logger logger = LoggerFactory.getLogger (ApiGatewayController.class);
	
	
	public ApiGatewayController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping (value = "/ping", method = RequestMethod.GET)
	public StatusVO ping (Locale locale)
	{
		logger.debug ("Received Ping...");
		StatusVO response = new StatusVO ();	
		response.setStatus(true);
		response.setData("Versions: v1. Api Gateway is reachable.");
		this.logger.debug ("Returning response:{}", response);
		return response;
	}
}