package com.uas.service;

/**
*
* University Admission System
* 
* Application Service Implementation Class
* This is the service layer implementation for the Application architecture,
* with implementations for adding application, showing offered and scheduled program details, checking application status methods.
* 
* 
*
* @version 1.0
* @since   2019-10-29
*/

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.beans.Application;
import com.uas.beans.Program;
import com.uas.beans.ProgramScheduled;
import com.uas.dao.ApplicationDAO;
import com.uas.dao.ApplicationDAOImpl;

public class ApplicationServiceImpl implements ApplicationService {

	ApplicationDAO appDetails = null;

	public ApplicationServiceImpl() {
		appDetails = new ApplicationDAOImpl();
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	final Logger logger = Logger.getLogger(ApplicationServiceImpl.class);

	@Override
	public int addApplicationDetails(Application app) {
		logger.info("Adding Application Details in ApplicationService Layer");
		return appDetails.addApplicationDetails(app);
	}

	@Override
	public List<ProgramScheduled> showScheduledDetails() {
		logger.info("Listing Scheduled Programs Details in ApplicationService Layer");
		return appDetails.showScheduledDetails();
	}

	@Override
	public List<Program> showDetails() {
		logger.info("Listing Offered Programs Details in ApplicationService Layer");
		return appDetails.showDetails();
	}

	@Override
	public String checkStatus(int id) {
		logger.info("Tracking Application Status using Application ID in ApplicationService Layer");
		return appDetails.checkStatus(id);
	}

}
