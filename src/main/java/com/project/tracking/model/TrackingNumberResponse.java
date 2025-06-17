package com.project.tracking.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackingNumberResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "tracking_number")
	private String trackingNumber;
	
	@JsonProperty(value = "created_at")
	private OffsetDateTime createdAt;
	
	@JsonProperty(value = "origin")
	private String origin;
	
	@JsonProperty(value = "destination")
	private String destination;
	
	@JsonProperty(value = "customer_name")
	private String customerName;
	
	@JsonProperty(value = "weight")
	private Double weight;
	

	public TrackingNumberResponse() {
		super();
	}

	public TrackingNumberResponse(String trackingNumber, OffsetDateTime createdAt, String origin, String destination,
			String customerName, Double weight) {
		super();
		this.trackingNumber = trackingNumber;
		this.createdAt = createdAt;
		this.origin = origin;
		this.destination = destination;
		this.customerName = customerName;
		this.weight = weight;
	}
	

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
