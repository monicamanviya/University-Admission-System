package com.uas.service;

/**
*
* University Admission System
* 
* MAC Service Implementation Class
* This is the service layer implementation for the MAC architecture,
* with implementations of methods for showing applicants details, updating their status or interview date, deleting applicant, and showing status.
* 
* 
*
* @version 1.0
* @since   2019-10-29
*/

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.dao.MACdao;
import com.uas.dao.MACdaoImpl;

public class MACserviceImpl implements MACservice {

	MACdao mac = null;

	public MACserviceImpl() {
		mac = new MACdaoImpl();
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	final Logger logger = Logger.getLogger(MACserviceImpl.class);

	@Override
	public void showDetails() {
		mac.showDetails();
		logger.info("Showing Applicants Details in MACService Layer");
	}

	@Override
	public int updateStatus(int app_id, String status) {

		logger.info("Updating Applicants Details in MACService Layer");
		return mac.updateStatus(app_id, status);
	}

	@Override
	public void updateInterviewDate(int app_id, String date_of_interview) {

		mac.updateInterviewDate(app_id, date_of_interview);
		logger.info("Updating Interview Date for applicants in MACService Layer");
	}

	@Override
	public int confirmationStatus(int idd, String status) {
		logger.info("Confirming Applicants Status in MACService Layer");
		return mac.confirmationStatus(idd, status);
	}

	@Override
	public void deleteApplicant(int app_id) {
		mac.deleteApplicant(app_id);
		logger.info("Deleting Applicants Details in MACService Layer");
	}

	@Override
	public String showStatus(int app_id) {
		logger.info("Retrieving Applicant Status in MACService Layer");
		return mac.showStatus(app_id);
	}

}
