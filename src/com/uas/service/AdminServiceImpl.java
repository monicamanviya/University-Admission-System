package com.uas.service;

/**
*
* University Admission System
* 
* Admin Service Implementation Class
* This is the service layer implementation for the Admin architecture,
* with methods for showing applicants details, adding, deleting or viewing offered/scheduled programs.
* 
* 
*
* @version 1.0
* @since   2019-10-29
*/
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.beans.Program;
import com.uas.beans.ProgramScheduled;
import com.uas.dao.AdminDao;
import com.uas.dao.AdminDaoImpl;

public class AdminServiceImpl implements AdminService {
	public AdminServiceImpl() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	final Logger logger = Logger.getLogger(AdminServiceImpl.class);

	AdminDao dao = new AdminDaoImpl();

	@Override
	public List<ProgramScheduled> showScheduledDetails() {
		logger.info("Listing Scheduled Programs Details in AdminServiceImpl Layer");
		return dao.showScheduledDetails();
	}

	@Override
	public List<Program> showDetails() {
		logger.info("Listing Offered Programs Details in AdminServiceImpl Layer");
		return dao.showDetails();
	}

	@Override
	public void showApplicantsDetails() {
		dao.showApplicantsDetails();
		logger.info("Showing Applicants Details in AdminServiceImpl Layer");
	}

	public void addDetails(Program prg) {
		dao.adddetails(prg);
		logger.info("Adding Programs Details in AdminServiceImpl Layer");
	}

	public void deleteDetails(String p) {
		dao.deleteDetails(p);
		logger.info("Deleting Programs Details in AdminServiceImpl Layer");
	}

	@Override
	public void deleteScheduledDetails(String p) {
		dao.deleteScheduledDetails(p);
		logger.info("Deleting Scheduled Programs Details in AdminServiceImpl Layer");
	}

}
