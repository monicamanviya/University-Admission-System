package com.uas.ui;

/**
*
* University Admission System
* 
* Admin UI Class:
* The Admin can list all the courses offered or scheduled,
* add or delete the programs in these lists.
* 
* 
*
*
* @version 1.0
* @since   2019-10-29
*/

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.beans.Program;
import com.uas.beans.ProgramScheduled;
import com.uas.exception.ApplicationException;
import com.uas.service.AdminService;
import com.uas.service.AdminServiceImpl;

public class AdminUI {

	public AdminUI() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	final Logger logger = Logger.getLogger(AdminUI.class);

	public void getAdminUI() {

		Program prg = new Program();
		Scanner sc = new Scanner(System.in);

		String ch = null;

		do {

			AdminService service = new AdminServiceImpl();
			int choice1 = 0;
			String p = null;

			System.out.println("Enter the operation you want to perform");
			System.out.println("1.List of Programs Offered");
			System.out.println("2.List of Applicant Details");
			System.out.println("3.Add Program");
			System.out.println("4.Delete Program");
			choice1 = sc.nextInt();
			switch (choice1) {
			case 1: {
				System.out.println("--------List of Available Programs---------\n");

				List<Program> list1 = service.showDetails();
				if (list1.size() > 0) {
					Iterator<Program> it = list1.iterator();
					while (it.hasNext())
						System.out.println(it.next() + " ");
				} else {
					throw new ApplicationException("Programs not found");
				}

				logger.info("Listing Programs Offered in Admin UI");
				break;
			}
			case 2: {
				System.out.println("--------List of Applicants---------\n");
				service.showApplicantsDetails();
				logger.info("Listing Applicants Details in Admin UI");
				break;
			}
			case 3: {
				service.addDetails(prg);
				logger.info("Adding Programs in Admin UI");
				break;

			}
			case 4: {
				System.out.println("--------Delete Program---------\n");

				System.out.println("Enter the program to be deleted: ");
				System.out.println("choose 1 for Delete from Programs Offered:");
				System.out.println("choose 2 for Delete from Programs Scheduled:");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("--------List of Programs-------");
					List<Program> list1 =service.showDetails();
					Iterator<Program> itr = list1.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
					
					System.out.println();
					System.out.println("Enter the program to be deleted: ");
					p = sc.next();
					service.deleteDetails(p);
					break;
				case 2:
					System.out.println("--------List of Programs-------");
					List<ProgramScheduled> list2 =service.showScheduledDetails();
					Iterator<ProgramScheduled> itr1 = list2.iterator();
					while (itr1.hasNext()) {
						System.out.println(itr1.next());
					}
	
					System.out.println();
					System.out.println("Enter the program to be deleted: ");
					p = sc.next();
					service.deleteScheduledDetails(p);
					logger.info("Deleting Programs in Admin UI");
					break;
				default:
					break;
				}

			}
			}
			System.out.println("Do You Want To Continue press y/n");
			ch = sc.next();
		} while (ch.equals("y"));
	}
}
