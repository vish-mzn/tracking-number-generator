package com.project.tracking.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebFilter("/*")
public class LoggingFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);
	private static final int MAX_BODY_SIZE = 10 * 1024; // 10 KB
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
		
		try {
			// Process the request with wrapped objects
			chain.doFilter(wrappedRequest, wrappedResponse);
		} finally {
			// log before processing
			logRequest(httpRequest);
//			logRequest(wrappedRequest);
			logResponse(wrappedResponse);
			
			wrappedResponse.copyBodyToResponse(); // this Ensure response is forwarded correctly
		}
	}
	
	
	private void logRequest(HttpServletRequest request) {
        String params = getParameterString(request);
        log.info("INCOMING REQUEST: Method={}, Path={}, Params={}",
                request.getMethod(), request.getRequestURI(), params);
    }
	
	private void logRequest(ContentCachingRequestWrapper request) {
		try {
			String requestBody = getBodyAsString(request.getContentAsByteArray());
			log.info("INCOMING REQUEST: Method={}, Path={}, Body={}",
					request.getMethod(), request.getRequestURI(), requestBody);
		} catch (Exception e) {
			log.error("Failed to log request", e);
		}
	}
	
	private void logResponse(ContentCachingResponseWrapper response) {
	    try {
	      String responseBody = getBodyAsString(response.getContentAsByteArray());
	      log.info("OUTGOING RESPONSE: Status={}, Body={}", 
	    		  response.getStatus(), responseBody);
	    } catch (Exception e) {
	      log.error("Failed to log response", e);
	    }
	}
	
	private String getBodyAsString(byte[] content) {
		return (content.length > MAX_BODY_SIZE) ? "[BODY TOO LARGE]" : 
			new String(content, StandardCharsets.UTF_8);
	}
	
	private String getParameterString(HttpServletRequest request) {
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap.isEmpty()) return "{}";

        return paramMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                .collect(Collectors.joining(", ", "{", "}"));
    }

}
