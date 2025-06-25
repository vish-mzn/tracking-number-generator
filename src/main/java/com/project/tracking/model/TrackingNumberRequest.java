package com.project.tracking.model;

import java.time.OffsetDateTime;

public record TrackingNumberRequest(
		String origin, 
		String destination, 
		Double weight,
		OffsetDateTime createdAt,
		String customerId,
		String customerName,
		String customerSlug) {
	
	
	public static TrackingNumberRequest of(
			String origin, 
			String destination, 
			Double weight,
			OffsetDateTime createdAt,
			String customerId,
			String customerName,
			String customerSlug) {
		return new TrackingNumberRequest(
				origin,
				destination,
				weight,
				createdAt,
				customerId,
				customerName,
				customerSlug);
	}
	
	
}
