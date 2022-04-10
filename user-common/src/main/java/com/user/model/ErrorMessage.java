package com.user.model;

import java.time.LocalDateTime;

public class ErrorMessage {
    private String errorMessage;
    private Object errors;
    private LocalDateTime timeStamp;

	public ErrorMessage(String errorMessage,LocalDateTime timeStamp) {
		this.errorMessage = errorMessage;
		this.timeStamp=timeStamp;
	}
	public ErrorMessage(String errorMessage,Object errors) {
		this.errorMessage = errorMessage;
		this.errors = errors;
	}

	public ErrorMessage(String errorMessage,Object errors,LocalDateTime timeStamp) {
		this.errorMessage = errorMessage;
		this.errors = errors;
		this.timeStamp=timeStamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
    
}
