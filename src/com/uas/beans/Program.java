package com.uas.beans;

/**
 *
 * University Admission System
 * 
 * Program Bean Class: The program bean has the program table columns as the
 * class attributes.
 * 
 *
 * @version 1.0
 * @since 2019-10-29
 */

public class Program {

	String program;
	String description;
	String eligibility;
	int duration;
	String degree;

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Override
	public String toString() {
		return "Program [program=" + program + ", description=" + description + ", eligibility=" + eligibility
				+ ", duration=" + duration + ", degree=" + degree + "]";
	}

}
