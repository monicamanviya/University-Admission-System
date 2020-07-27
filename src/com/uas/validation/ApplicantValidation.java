package com.uas.validation;

/**
*
* University Admission System
* 
* Applicant Validation Class:
* Validation methods for full name, date, percent, and email.
* 
*
*
* @version 1.0
* @since   2019-10-29
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicantValidation {

	public static boolean validateLetters(String txt) {

		String regx = "^[a-zA-Z\\s]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();

	}

	public static boolean isValidDate(String d) {
		String regex = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";

		Pattern pattern1 = Pattern.compile(regex);
		Matcher matcher1 = pattern1.matcher((CharSequence) d);
		return matcher1.matches();
	}

	public static boolean isValidPercent(double d) {
		if (d > 0 && d < 100)
			return true;
		else
			return false;
	}

	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*1-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

}
