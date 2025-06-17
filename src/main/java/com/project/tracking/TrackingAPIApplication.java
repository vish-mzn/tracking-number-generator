package com.project.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Spring Boot Application
 * This class bootstraps the application using Spring Boot’s auto-configuration.
 */
@SpringBootApplication
public class TrackingAPIApplication {

	/**
	 * Main method that starts the Spring Boot application.
	 * 
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		// Launches the Spring Boot application
		SpringApplication.run(TrackingAPIApplication.class, args);
	}

}
