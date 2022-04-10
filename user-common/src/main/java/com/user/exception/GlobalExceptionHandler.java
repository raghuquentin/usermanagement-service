package com.user.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.model.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public Object handleLoginAuthenticationException(Exception ex){
        ErrorMessage message = new ErrorMessage(ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<Object>(message,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UniqueDataException.class)
    public Object handleDataIntegrity(Exception ex){
        ErrorMessage message = new ErrorMessage(ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<Object>(message,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(EntityNotFoudByIdException.class)
    public Object handleEntityNotFoundException(Exception ex){
        ErrorMessage message = new ErrorMessage(ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<Object>(message,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    	List<String> errors = new ArrayList<String>();
    	for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + " -> " + error.getDefaultMessage());
		}
        ErrorMessage message = new ErrorMessage(ex.getLocalizedMessage(),errors,LocalDateTime.now());
        return new ResponseEntity<Object>(message,HttpStatus.BAD_REQUEST);
    }
}
