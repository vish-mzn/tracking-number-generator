package com.project.tracking.util;

import java.time.OffsetDateTime;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationUtil {
	
	public static final Logger LOG = LoggerFactory.getLogger(ValidationUtil.class);
	
	private static final Pattern ISO_COUNTRY_CODE_PATTERN = Pattern.compile("^[A-Z]{2}$");
	
	public static boolean resolveOrigin(String origin) {
		boolean invalid = origin == null || !ISO_COUNTRY_CODE_PATTERN.matcher(origin).matches();
        if (invalid) LOG.warn("Invalid origin_country_id: {}", origin);
        return invalid;
	}
	
	public static boolean resolveDestination(String destination) {
		boolean invalid = destination == null || !ISO_COUNTRY_CODE_PATTERN.matcher(destination).matches();
        if (invalid) LOG.warn("Invalid destination_country_id: {}", destination);
        return invalid;
	}
	
	public static boolean resolveWeight(Double weight) {
		boolean invalid = (weight == null || weight <= 0.01 || weight > 999.999);
        if (invalid) LOG.warn("Invalid weight: {}", weight);
        return invalid;
	}
	
	public static boolean resolveCreatedAt(OffsetDateTime createdAt) {
		boolean invalid = (createdAt == null || createdAt.isAfter(OffsetDateTime.now()));
        if (invalid) LOG.warn("Invalid createdAt: {}", createdAt);
        return invalid;
	}
	
	public static boolean resolveCustomerId(String customerId) {
		boolean invalid = (customerId == null || customerId.trim().isEmpty() || customerId.length() > 36);
		if (invalid) LOG.warn("Invalid customerId: {}", customerId);
		return invalid;
	}
	

}
