package com.uas.service;

/**
*
* University Admission System
* 
* Admin Service Interface
* This is the service layer for the Admin architecture,
* with methods for showing applicants details, adding, deleting or viewing offered/scheduled programs.
* 
* 
*
* @version 1.0
* @since   2019-10-29
*/

import java.util.List;

import com.uas.beans.Program;
import com.uas.beans.ProgramScheduled;

public interface AdminService {
	void showApplicantsDetails();

	public void addDetails(Program prg);

	public List<Program> showDetails();

	public List<ProgramScheduled> showScheduledDetails();

	public void deleteDetails(String p);

	public void deleteScheduledDetails(String p);
}
