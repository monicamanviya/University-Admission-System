package com.uas.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.exception.ProgramsException;

/**
 *
 * University Admission System
 * 
 * Main UI Class: The user can be: Applicant, Member of Admissions
 * Committee(MAC) or Administrator. While the applicant can apply for the
 * offered courses or track the application status, the latter two have been
 * provided with the login facility.
 * 
 * 
 *
 *
 * @version 1.0
 * @since 2019-10-29
 */

public class MainUI {

	public static void main(String[] args) {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
		final Logger logger = Logger.getLogger(MacUI.class);
		int choice;
		Scanner sc = new Scanner(System.in);
		LoginUI login = new LoginUI();
		ApplicantUI app = new ApplicantUI();

		do {
			System.out.println("----------------------------------------------");
			System.out.println("===Welcome to University Admission System  ===");
			System.out.println("Enter Choice:");
			System.out.println("1.Do You Want To Apply for a Course");
			System.out.println("2.Track Your Application Status");
			System.out.println("3.Login");
			System.out.println("4.Exit");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				try {
					app.apply();
				} catch (ProgramsException e) {
					System.err.println(e.getMessage());
				}
				break;
			case 2:
				app.checkStatus();
				break;
			case 3:
				login.getLogin();
				break;
			case 4:
				logger.info("Program ended");
				System.exit(0);

				break;
			default:
				break;
			}
			logger.info("In Main UI");
		} while (choice != 4);

	}
}
