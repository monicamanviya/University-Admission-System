package com.uas.beans;

/**
 *
 * University Admission System
 * 
 * Application Bean Class: The application bean has the fields for the
 * application form as the class attributes.
 * 
 *
 * @version 1.0
 * @since 2019-10-29
 */
public class Application {
	int app_id;
	String full_name;
	String dob;
	String highest_qualification;
	double marks;
	String goals;
	String email;
	String sch_prog_id;
	String status;
	String date_of_interview;

	public Application() {

	}

	public Application(int app_id, String full_name, String dob, String highest_qualification, double marks,
			String goals, String email, String sch_prog_id, String status, String date_of_interview) {
		super();
		this.app_id = app_id;
		this.full_name = full_name;
		this.dob = dob;
		this.highest_qualification = highest_qualification;
		this.marks = marks;
		this.goals = goals;
		this.email = email;
		this.sch_prog_id = sch_prog_id;
		this.status = status;
		this.date_of_interview = date_of_interview;
	}

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getHighest_qualification() {
		return highest_qualification;
	}

	public void setHighest_qualification(String highest_qualification) {
		this.highest_qualification = highest_qualification;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSch_prog_id() {
		return sch_prog_id;
	}

	public void setSch_prog_id(String sch_prog_id) {
		this.sch_prog_id = sch_prog_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate_of_interview() {
		return date_of_interview;
	}

	public void setDate_of_interview(String date_of_interview) {
		this.date_of_interview = date_of_interview;
	}

	@Override
	public String toString() {
		return "Application [app_id=" + app_id + ", full_name=" + full_name + ", dob=" + dob
				+ ", highest_qualification=" + highest_qualification + ", marks=" + marks + ", goals=" + goals
				+ ", email=" + email + ", sch_prog_id=" + sch_prog_id + ", status=" + status + ", date_of_interview="
				+ date_of_interview + "]";
	}

}
