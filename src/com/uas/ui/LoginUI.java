package com.uas.ui;

/**
*
* University Admission System
* 
* Login UI Class:
* It is the UI Layer of the Login architecture.
* Username and Password are passed to the service layer through this layer.  
* 
*
*
* @version 1.0
* @since   2019-10-29
*/

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.beans.Login;
import com.uas.exception.LoginException;
import com.uas.service.LoginService;
import com.uas.service.LoginServiceImpl;

import oracle.net.aso.e;

public class LoginUI {
	public LoginUI() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	final Logger logger = Logger.getLogger(LoginUI.class);

	void getLogin() {
		String user, pass, role;
		MacUI mac = new MacUI();
		AdminUI admin = new AdminUI();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter valid username");
		user = sc.next();
		System.out.println("Enter valid password");
		pass = sc.next();

		Login l = new Login(user, pass);

		LoginService obj = new LoginServiceImpl();
		try {
			role = obj.authenticate(user, pass);
			if (role.equalsIgnoreCase("MAC")) {

				System.out.println("Welcome MAC");
				mac.getMacUI();

			}

			if (role.equalsIgnoreCase("ADMIN")) {
				System.out.println("Welcome ADMIN");
				admin.getAdminUI();

			}
			if (role.equals("null")) {
				System.out.println("--------");
			}
		} catch (LoginException e) {
			System.err.println(e.getMessage());
		}
		logger.info("Login method in Login UI");
	}
}
