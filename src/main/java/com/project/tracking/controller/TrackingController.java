package com.project.tracking.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tracking.exception.Error;
import com.project.tracking.exception.InputValidationException;
import com.project.tracking.model.TrackingNumberRequest;
import com.project.tracking.model.TrackingNumberResponse;
import com.project.tracking.service.TrackingService;
import com.project.tracking.util.ValidationUtil;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/next-tracking-number")
public class TrackingController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TrackingController.class);
	
	private final TrackingService trackingService;
	
	public TrackingController(TrackingService trackingService) {
		this.trackingService = trackingService;
	}
	
	
	
	@GetMapping
	public ResponseEntity<TrackingNumberResponse> generateTrackingNumber(
			@RequestParam("origin_country_id") String origin,
			@RequestParam("destination_country_id") String destination,
			@RequestParam Double weight,
			@RequestParam("created_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime createdAt,
			@RequestParam("customer_id") String customerId,
			@RequestParam("customer_name") String customerName,
			@RequestParam("customer_slug") String customerSlug) {
		LOG.info("inside controller - generateTrackingNumber");
		
		TrackingNumberRequest request = 
				TrackingNumberRequest.of(origin, destination, weight, createdAt, customerSlug, customerName, customerSlug);
		validateRequest(request);
		LOG.info("exit controller - generateTrackingNumber");
		return ResponseEntity.ok(trackingService.generateTrackingNumber(origin, destination, createdAt, customerSlug, weight));
	}
	
	
	
	private boolean validateRequest(TrackingNumberRequest request) {
		var errorList = new ArrayList<Error>();
		boolean errorFlag = false;
		
		if (ValidationUtil.resolveOrigin(request.origin())) {
			errorList.add(new Error("origin", "The origin value is wrong"));
			errorFlag = true;
		} 
		if (ValidationUtil.resolveDestination(request.destination())) {
			errorList.add(new Error("destination", "The destination value is wrong"));
			errorFlag = true;
		}
		if (ValidationUtil.resolveWeight(request.weight())) {
			errorList.add(new Error("weight", "The parcel is over weight"));
			errorFlag = true;
		}
		if (ValidationUtil.resolveCreatedAt(request.createdAt())) {
			errorList.add(new Error("createdAt", "The createdAt value is wrong"));
			errorFlag = true;
		}
		if (ValidationUtil.resolveCustomerId(request.customerId())) {
			errorList.add(new Error("customerId", "The customerId value is wrong"));
			errorFlag = true;
		}
		
		if (errorFlag) {
			throw new InputValidationException(errorList);
		}
		return true;
	}

}
