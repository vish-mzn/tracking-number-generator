package com.project.tracking.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private List<Error> errorList;
	private static final String message = "Exception caught in the request";
	
	
	public InputValidationException(List<Error> list) {
		super(message);
		this.errorList = list;
	}


	public List<Error> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<Error> errorList) {
		this.errorList = errorList;
	}
}
