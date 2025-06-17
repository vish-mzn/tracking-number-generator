package com.project.tracking.service;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.tracking.model.TrackingNumberResponse;
import com.project.tracking.util.NumberGeneratorUtil;

@Service
public class TrackingService {
	
	private static final Logger LOG = LoggerFactory.getLogger(TrackingService.class);
	
	
	public TrackingNumberResponse generateTrackingNumber(String origin, String destination, OffsetDateTime created_At, 
			String customer_name, Double weight) {
		LOG.info("inside service - generating tracking number for: {}" + customer_name);
		
		// Add the origin, destination, customer checking, validate the 3 details from the database
		// you can add custom validation - like particular destination is not serviceable for sometime - please try after sometime
		// or you can add validation like - particular customer service is halted - please contact back office
		
		String uniqueNumber = NumberGeneratorUtil.generateUniqueNumber(origin, destination, created_At);
		
		TrackingNumberResponse response = 
				new TrackingNumberResponse(uniqueNumber, created_At, origin, destination, customer_name, weight);
		
		return response;
	}
	

}
