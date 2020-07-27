package com.uas.dao;

/**
 *
 * University Admission System
 * 
 * MAC DAO Interface This is the DAO layer for the MAC architecture, with
 * methods for showing applicants details, updating their status or interview
 * date, deleting applicant, and showing status.
 * 
 * 
 *
 * @version 1.0
 * @since 2019-10-29
 */

public interface MACdao {

	void showDetails();

	int updateStatus(int app_id, String status);

	void updateInterviewDate(int app_id, String date_of_interview);

	int confirmationStatus(int app_id, String status);

	void deleteApplicant(int app_id);

	String showStatus(int app_id);
}
