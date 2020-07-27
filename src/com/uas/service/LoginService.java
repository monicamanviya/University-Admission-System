package com.uas.service;

/**
 *
 * University Admission System
 * 
 * Login Service Interface This is the service layer for the Login architecture,
 * with authentication method.
 * 
 * 
 *
 * @version 1.0
 * @since 2019-10-29
 */

public interface LoginService {

	public String authenticate(String user, String pass);

}
