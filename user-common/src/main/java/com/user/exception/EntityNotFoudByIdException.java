package com.user.exception;

/**
 * 
 * custom exception throw when deleting the record by id, if no record is not found for the given id
 * @author araghi
 *
 */
public class EntityNotFoudByIdException extends RuntimeException{
	public EntityNotFoudByIdException() {
		super();
	}

	public EntityNotFoudByIdException(String msg) {
		super(msg);
	}
}
