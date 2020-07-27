package com.uas.ui;

/**
*
* University Admission System
* 
* Applicant UI Class:
* This UI layer accepts the application details from the user and passes them to the service layer.
* The applicant can also track the status of the application.
* 
* 
*
*
* @version 1.0
* @since   2019-10-29
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.beans.Application;
import com.uas.beans.ProgramScheduled;
import com.uas.exception.ProgramsException;
import com.uas.exception.StatusException;
import com.uas.service.AdminServiceImpl;
import com.uas.service.ApplicationService;
import com.uas.service.ApplicationServiceImpl;
import com.uas.validation.ApplicantValidation;

public class ApplicantUI {

	public ApplicantUI() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	final Logger logger = Logger.getLogger(ApplicantUI.class);

	public void apply() {

		Scanner sc = new Scanner(System.in);
		String input = null;

		Application app = new Application();

		ApplicationService appServ = new ApplicationServiceImpl();
		ApplicantValidation appVal = new ApplicantValidation();

		System.out.println("----List of all Programs-----");

		List<ProgramScheduled> list1 = new ArrayList<ProgramScheduled>();
		list1 = appServ.showScheduledDetails();
		if (list1.size() > 0) {
			Iterator<ProgramScheduled> itr = list1.iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());

			}
		} else {
			logger.error("No Programs Scheduled");
			throw new ProgramsException("No Programs Scheduled");
		}
		int id_seq = app.getApp_id();
		System.out.println("enter Applicant's full name:");
		input = sc.nextLine();
		while (!appVal.validateLetters(input)) {
			System.out.println("enter valid Applicant's full name :");
			input = sc.nextLine();
		}
		app.setFull_name(input);
		System.out.println("enter date of birth in format DD/MM/YYYY");
		input = sc.next();
		while (!appVal.isValidDate(input)) {
			System.out.println("enter valid Applicant's date of birth in format DD/MM/YYYY:");
			input = sc.next();
		}
		app.setDob(input);

		System.out.println("Choose Highest Qualification");
		System.out.println("1.B.tech");
		System.out.println("2.M.tech");
		System.out.println("select choice....");
		int c = sc.nextInt();
		switch (c) {
		case 1:
			app.setHighest_qualification("B.tech");
			System.out.println("Selected choice B.tech");
			break;
		case 2:
			app.setHighest_qualification("M.tech");
			System.out.println("Selected choice M.tech");
			break;
		default:
			System.out.println("invalid choice");
			break;
		}

		System.out.println(" Enter Marks obtained in Highest Qualification in percent:");
		double m = sc.nextDouble();
		while (!appVal.isValidPercent(m)) {
			System.out.println("enter valid Applicant's Highest Qualification in percent:");
			m = sc.nextDouble();
		}
		app.setMarks(m);

		System.out.println("enter your goals");
		input = sc.next();
		app.setGoals(input);

		System.out.println("enter your email-id");
		input = sc.next();
		while (!appVal.isValidEmail(input)) {
			System.out.println("enter valid email:");
			input = sc.next();
		}
		app.setEmail(input);

		System.out.println("enter Scheduled_Program_id ");
		System.out.println("Choose the program id from the scheduled programs list:");

		List<ProgramScheduled> list2 = new ArrayList<ProgramScheduled>();
		list2 = appServ.showScheduledDetails();
		Iterator<ProgramScheduled> itr1 = list2.iterator();
		while (itr1.hasNext()) {
			System.out.println(itr1.next());

		}
		input = sc.next();
		app.setSch_prog_id(input);

		System.out.println("current  status : applied");
		input = "applied";
		app.setStatus(input);

		input = "none";
		app.setDate_of_interview(input);
		int rows = appServ.addApplicationDetails(app);

		logger.info("Adding Applicants Details in ApplicantUI");

	}

	public void checkStatus() {
		try {
			Scanner sc = new Scanner(System.in);
			ApplicationService appServ = new ApplicationServiceImpl();
			System.out.println("Enter your application Id");
			int id = sc.nextInt();
			String currentStatus = appServ.checkStatus(id);

			logger.info("Checking Status of Applicant in ApplicantUI");
		} catch (StatusException e) {
			System.err.println(e.getMessage());
		}
	}

}
