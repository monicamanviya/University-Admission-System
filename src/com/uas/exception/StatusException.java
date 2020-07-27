package com.uas.exception;

/**
 *
 * University Admission System
 * 
 * Application Status Exception Class: The exception for the application status
 * Error Message.
 * 
 *
 *
 * @version 1.0
 * @since 2019-10-29
 */
public class StatusException extends RuntimeException {
	public StatusException(String msg) {
		super(msg);
	}

}
