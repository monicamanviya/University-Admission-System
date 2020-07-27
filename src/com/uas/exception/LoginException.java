package com.uas.exception;

/**
 *
 * University Admission System
 * 
 * Login Exception Class: The exception for the login Error Message.
 * 
 *
 *
 * @version 1.0
 * @since 2019-10-29
 */
public class LoginException extends RuntimeException {
	public LoginException(String msg) {
		super(msg);
	}

}
