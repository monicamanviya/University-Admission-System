package com.uas.dao;

/**
 *
 * University Admission System
 * 
 * Login DAO Interface This is the DAO layer for the Login architecture, with
 * authentication method.
 * 
 * 
 *
 * @version 1.0
 * @since 2019-10-29
 */

public interface LoginDao {
	public String authenticate(String user, String pass);
}
