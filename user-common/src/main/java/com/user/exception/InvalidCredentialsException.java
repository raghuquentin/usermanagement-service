package com.user.exception;

/**
 * Custom exception throws when user trying login, who passed invalid
 * login credentials
 */
public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){super();}
    public InvalidCredentialsException(String msg){
        super(msg);
    }
}
