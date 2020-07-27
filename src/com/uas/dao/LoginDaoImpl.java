package com.uas.dao;

/**
*
* University Admission System
* 
* Login DAO Implementation Class
* This is the DAO layer for the Login architecture,
* with authentication method's database access object implementation.
* 
* 
*
* @version 1.0
* @since   2019-10-29
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.exception.LoginException;
import com.uas.util.ConnectionFactory;

public class LoginDaoImpl implements LoginDao {
	public LoginDaoImpl() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	final Logger logger = Logger.getLogger(LoginDaoImpl.class);

	ConnectionFactory conFactory = ConnectionFactory.getInstance();

	public String authenticate(String user, String pass) {
		String SQL1 = "SELECT * FROM LOGIN WHERE login_id=? AND password = ? ";
		String role1 = null;
		try (Connection conn = conFactory.getConnection(); PreparedStatement pstat = conn.prepareStatement(SQL1);) {
			pstat.setString(1, user);
			pstat.setString(2, pass);
			int rows = pstat.executeUpdate();
			if (rows == 0) {
				logger.error("Login Not Found");
				throw new LoginException("Login Not Found");
			} else {
				ResultSet rs = pstat.executeQuery(SQL1);
				while (rs.next()) {

					role1 = rs.getString("role");
				}
				logger.info("Login Authentication method in Login DAO Impl");

			}
		} catch (SQLException e) {

		}
		return role1;
	}

}
