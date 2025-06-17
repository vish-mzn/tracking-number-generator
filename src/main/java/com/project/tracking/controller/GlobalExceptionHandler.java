package com.project.tracking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.tracking.exception.Error;
import com.project.tracking.exception.InputValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(InputValidationException.class)
	public ResponseEntity<List<Error>> handleInputValidationException(InputValidationException ex) {
		return new ResponseEntity<>(ex.getErrorList(), HttpStatus.BAD_REQUEST);		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlGeneralException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
