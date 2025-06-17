package com.project.tracking.util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberGeneratorUtil {
	
	public static final Logger LOG = LoggerFactory.getLogger(NumberGeneratorUtil.class);
	
	
	public static String generateUniqueNumber(String origin, String destination, OffsetDateTime createdAt) {
		LOG.info("inside generatorUtil:  generating unique number");
		
		String date = createdAt.format(DateTimeFormatter.ofPattern("yyMMdd"));
		
		int uuidHash = Math.abs(UUID.randomUUID().hashCode()) & 0xFFFFFF;
		
		String base36Hash = Integer.toString(uuidHash, 36).toUpperCase();
		String paddedHash = String.format("%6s", base36Hash).replace(' ', '0');
		
		String trackingNumber = String.join("", origin, destination, date, paddedHash);
		
		LOG.info("------ Happy Tracking ------");
		LOG.info("next Unique tracking number:  {}", trackingNumber);
		return trackingNumber;
	}

}
