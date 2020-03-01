/**
 * 
 */
package com.harness.explorer.apigateway.filter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ForwardedHeadersZuulPreFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(ForwardedHeadersZuulPreFilter.class);

    @Override
    public Object run() {
	RequestContext ctx = RequestContext.getCurrentContext();	
	Map<String, String> headers = ctx.getZuulRequestHeaders();
	log.debug("Input Headers:{}", headers);
	// Rely on HttpServletRequest to retrieve the correct remote address
	// from upstream X-Forwarded-For header	
	ctx.addZuulRequestHeader("host",  ctx.getRequest().getHeader("host"));		
	log.debug("Output Headers:{}", ctx.getZuulRequestHeaders());
	return null;	
    }

    @Override
    public boolean shouldFilter() {
	return true;
    }

    @Override
    public String filterType() {
	return "pre";
    }

    @Override
    public int filterOrder() {
	return 10000;
    }

}
