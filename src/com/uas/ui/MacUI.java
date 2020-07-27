package com.uas.ui;

/**
*
* University Admission System
* 
* MAC UI Class:
* This UI layer provides the option for the MAC member to:
* Update the status of various application.
* 
* Interview date can be set and the application can also be accepted, confirmed or rejected.
*
*
* @version 1.0
* @since   2019-10-29
*/

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.service.MACservice;
import com.uas.service.MACserviceImpl;

public class MacUI {

	public MacUI() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	final Logger logger = Logger.getLogger(MacUI.class);

	public void getMacUI() {
		MACservice mac = new MACserviceImpl();
		Scanner sc = new Scanner(System.in);
		mac.showDetails();
		String ch;
		do {
			System.out.println(
					"Enter 1 for accepting or rejecting the status of candidate and setting up an interview date. ");
			System.out.println("Enter 2 for confirming or rejecting the application");
			int choice = sc.nextInt();

			switch (choice) {
			case 1: {
				System.out.println("select id");
				int idd = sc.nextInt();
				String status = mac.showStatus(idd);
				System.out.println(status);
				if (status.equals("applied")) {

					if (status.equals("accepted")) {
						System.out.println("Status of the candidate is already 'accepted', waiting for confirmation");
						System.out.println("show updated table.................");
						mac.showDetails();

					} else if (status.equals("confirmed")) {
						System.out.println("Status of the candidate can not be changed.");
						System.out.println("show updated table.................");
						mac.showDetails();

					}

					else {
						System.out.println("Do you want to accept /reject the candidate");
						System.out.println("Enter 1 to accept candidate");
						System.out.println("Enter 2 to reject candidate");
						int choice1 = sc.nextInt();
						if (choice1 == 1) {
							mac.updateStatus(idd, "accepted");
							status = "accepted";

							System.out.println("show updated table.................");
							mac.showDetails();
						}

						else {
							mac.confirmationStatus(idd, "rejected");
							status = "rejected";
							System.out.println("show updated table.................");
							mac.showDetails();
						}
					}
				}
				logger.info("Updating Status of Application in MAC UI");
				break;
			}

			case 2: {
				System.out.println("select id");
				int idd = sc.nextInt();
				String status = mac.showStatus(idd);
				if (status.equals("applied")) {
					System.out.println(
							"Status of the candidate can not be changed directly to confirmation without interview");
					System.out.println("show updated table.................");
					mac.showDetails();

				}
				if (status.equals("confirmed")) {
					System.out.println("Status of the candidate can not be changed.");
					System.out.println("show updated table.................");
					mac.showDetails();

				}
				if (status.equals("accepted")) {
					System.out.println("Enter 1 to confirm the candidate:");
					System.out.println("Enter 2 to reject the candidate:");
					int choice1 = sc.nextInt();
					if (choice1 == 1) {
						mac.confirmationStatus(idd, "confirmed");
						status = "confirmed";
						System.out.println("show updated table.................");
						mac.showDetails();

					}
					if (choice1 == 2) {
						mac.confirmationStatus(idd, "rejected");
						status = "rejected";
						System.out.println("show updated table.................");
						mac.showDetails();

					}
				}

				logger.info("Confirming or Rejecting Application in MAC UI");
				break;
			}
			}
			System.out.println("Do You Want To Continue Press yes/no");

			ch = sc.next();
		} while (ch.equals("yes"));
	}
}
