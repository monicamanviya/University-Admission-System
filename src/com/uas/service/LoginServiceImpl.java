package com.uas.service;

/**
*
* University Admission System
* 
* Login Service Implementation Class
* This is the service layer for the Login architecture,
* with authentication method implementation.
* 
* 
*
* @version 1.0
* @since   2019-10-29
*/

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.dao.LoginDao;
import com.uas.dao.LoginDaoImpl;

public class LoginServiceImpl implements LoginService {
	public LoginServiceImpl() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");

	}

	final Logger logger = Logger.getLogger(LoginServiceImpl.class);
	LoginDao dao = new LoginDaoImpl();

	public String authenticate(String user, String pass) {

		logger.info("Login Authentication in LoginService Layer");
		return dao.authenticate(user, pass);
	}

}
