package com.uas.dao;

/**
*
* University Admission System
* 
* Admin DAO Interface
* This is the DAO layer for the Admin architecture,
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

public interface AdminDao {
	void showApplicantsDetails();

	public List<Program> showDetails();

	public List<ProgramScheduled> showScheduledDetails();

	void adddetails(Program prg);

	void deleteDetails(String p);

	void deleteScheduledDetails(String p);

}
