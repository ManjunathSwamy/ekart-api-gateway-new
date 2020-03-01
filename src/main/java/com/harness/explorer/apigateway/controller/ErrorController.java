package com.harness.explorer.apigateway.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ErrorController extends AbstractErrorController {

	private final Logger logger = LoggerFactory.getLogger (ErrorController.class);
			
    private static final boolean INCLUDE_STACK_TRACE = false;
    
	@Value("${error.path:/error}")
    private String errorPath;

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${error.path:/error}", produces = "application/json;charset=UTF-8")
    public ResponseEntity error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,INCLUDE_STACK_TRACE);
		
		//This should only be when we are not able to forward the request
		HttpStatus responseStatus = HttpStatus.NOT_FOUND;
		body.put("error_code", "EmailMgr_Down");
		body.put("status", responseStatus.value());
		String errorMsg = getErrorMessage(request);
		body.put("message", errorMsg);
		logger.error("Responding HTTP code: {} with Message: {}",responseStatus.value(),errorMsg);
		return new ResponseEntity<Map<String, Object>>(body,responseStatus);
	}

    private String getErrorMessage(HttpServletRequest request) {
        final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
        return exc != null ? exc.getMessage() : "Unexpected error occurred";
    }

	
    
  
    
    
}