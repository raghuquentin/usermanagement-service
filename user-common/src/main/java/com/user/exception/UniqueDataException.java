package com.user.exception;

/**
 * Custom exception to catch and throw Data integrity exception
 * @author araghu
 *
 */
public class UniqueDataException extends RuntimeException {

	public UniqueDataException() {
		super();
	}

	public UniqueDataException(String msg) {
		super(msg);
	}
}
