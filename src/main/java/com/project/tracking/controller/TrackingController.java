package com.project.tracking.controller;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tracking.model.TrackingNumberResponse;
import com.project.tracking.service.TrackingService;

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
			@RequestParam("origin_country_id") @Pattern(regexp = "^[A-Z]{2}$") String origin,
			@RequestParam("destination_country_id") @Pattern(regexp = "^[A-Z]{2}$") String destination,
			@RequestParam @DecimalMin("0.001") @DecimalMax("999.999") Double weight,
			@RequestParam("created_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime createdAt,
			@RequestParam("customer_id") UUID customerId,
			@RequestParam("customer_name") String customerName,
			@RequestParam("customer_slug") String customerSlug) {
		LOG.info("inside controller - generateTrackingNumber");
		validateRequest();
		return ResponseEntity.ok(trackingService.generateTrackingNumber(origin, destination, createdAt, customerSlug, weight));
	}
	
	
	
	private boolean validateRequest() {
		return true;
	}

}
