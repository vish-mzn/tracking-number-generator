package com.project.tracking.exception;

public class Error {
	
	private String field;
	private String message;
	
	
	public Error() {
		super();
	}

	public Error(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}
	
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
