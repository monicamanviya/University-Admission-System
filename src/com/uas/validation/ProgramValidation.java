package com.uas.validation;

/**
*
* University Admission System
* 
* Program Validation Class:
* Validation for Program name, Degree Type
* 
*
*
* @version 1.0
* @since   2019-10-29
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramValidation {
	public static boolean validateProgram(String txt) {

		String regx = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();

	}

	public static boolean validateDegree(String txt) {

		String regx = "^[a-zA-Z\\.\\s]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();

	}

}
