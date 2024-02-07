package com.bike.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistsException(String msg) {
		super(msg);
	}
}
