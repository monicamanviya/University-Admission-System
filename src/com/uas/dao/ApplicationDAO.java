package com.uas.dao;

/**
*
* University Admission System
* 
* Application DAO Interface
* This is the DAO layer for the Application architecture,
* with adding application, showing offered and scheduled program details, checking application status methods.
* 
* 
*
* @version 1.0
* @since   2019-10-29
*/

import java.util.List;

import com.uas.beans.Application;
import com.uas.beans.Program;
import com.uas.beans.ProgramScheduled;

public interface ApplicationDAO {

	public int addApplicationDetails(Application app);

	public List<Program> showDetails();

	public List<ProgramScheduled> showScheduledDetails();

	public String checkStatus(int id);

}
