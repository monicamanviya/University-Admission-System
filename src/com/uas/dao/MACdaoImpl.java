package com.uas.dao;

/**
*
* University Admission System
* 
* MAC DAO Implementation Class
* This is the DAO layer implementation for the MAC architecture,
* with methods for showing applicants details, updating their status or interview date, deleting applicant, and showing status.
* 
* 
*
* @version 1.0
* @since   2019-10-29
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uas.beans.Application;
import com.uas.validation.ApplicantValidation;

public class MACdaoImpl implements MACdao {

	Scanner sc = new Scanner(System.in);

	// DB Connection properties...
	String dbDriver = "oracle.jdbc.driver.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
	String dbUsername = "hr";
	String dbPassword = "hr";

	private Connection connection;

	public MACdaoImpl() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
		try {
			Class.forName(dbDriver);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	final Logger logger = Logger.getLogger(MACdaoImpl.class);

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public String showStatus(int app_id) {
		Application app = new Application();
		String SQL = "select status from Application WHERE Application_id=" + app_id;
		int rows = 0;
		try (Connection connection = getConnection(); Statement stat = connection.createStatement();) {

			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()) {
				app.setStatus(rs.getString("status"));
			}
			logger.info("Showing Application Status in MACdaoImpl Layer");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return app.getStatus();
	}

	@Override
	public void showDetails() {
		Application app = new Application();
		List<Application> lists = new ArrayList<Application>();
		String SQL = "SELECT * FROM Application ORDER BY Application_id";
		try (Connection connection = getConnection(); Statement stat = connection.createStatement();) {
			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()) {

				app.setApp_id(rs.getInt("Application_id"));
				app.setFull_name(rs.getString("full_name"));
				app.setDob(rs.getString("date_of_birth"));
				app.setHighest_qualification(rs.getString("highest_qualification"));
				app.setMarks(rs.getInt("marks_obtained"));
				app.setGoals(rs.getString("goals"));
				app.setEmail(rs.getString("email_id"));
				app.setSch_prog_id(rs.getString("Scheduled_program_id"));
				app.setStatus(rs.getString("status"));
				app.setDate_of_interview(rs.getString("Date_Of_Interview"));
				lists.add(app);

				System.out.println(lists);
				logger.info("Showing Application Details in MACdaoImpl Layer");

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int updateStatus(int app_id, String status) {
		Application app = new Application();

		String SQL = "UPDATE Application SET status = ? WHERE  Application_id=?";
		int rows = 0;
		try (Connection connection = getConnection(); PreparedStatement pstat = connection.prepareStatement(SQL);) {

			pstat.setString(1, status);
			pstat.setInt(2, app_id);

			rows = pstat.executeUpdate();
			System.out.println("Status updated successfully.....");
			if ((status.equals("accepted")) && rows > 0) {

				updateInterviewDate(app_id, app.getDate_of_interview());

			}

			logger.info("Updating Application Status in MACdaoImpl Layer");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public void updateInterviewDate(int app_id, String date_of_interview) {
		ApplicantValidation appVal = new ApplicantValidation();
		System.out.println("enter date of interview in format DD/MM/YYYY");
		String updated_date = sc.next();
		while (!appVal.isValidDate(updated_date)) {
			System.out.println("enter valid interview date in format DD/MM/YYYY:");
			updated_date = sc.next();
		}
		String SQL = "UPDATE Application SET Date_Of_Interview = ? WHERE  Application_id=?";
		int rows = 0;
		try (Connection connection = getConnection(); PreparedStatement pstat = connection.prepareStatement(SQL);) {

			pstat.setInt(2, app_id);
			pstat.setString(1, updated_date);
			rows = pstat.executeUpdate();
			System.out.println("interview date updated successfully.....");
			logger.info("Updating Interview Date in MACdaoImpl Layer");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int confirmationStatus(int app_id, String status) {
		Application app = new Application();

		String SQL = "UPDATE Application SET status = ?, Date_Of_Interview='none' WHERE  Application_id= ?";
		int rows = 0;
		try (Connection connection = getConnection(); PreparedStatement pstat = connection.prepareStatement(SQL);) {
			pstat.setString(1, status);
			pstat.setInt(2, app_id);
			rows = pstat.executeUpdate();
			System.out.println("Status updated successfully.....");
			if (status.equals("rejected")) {

				deleteApplicant(app_id);

			}
			logger.info("Updating Confirmation Status of Application in MACdaoImpl Layer");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public void deleteApplicant(int app_id) {

		Application app = new Application();

		String SQL = "delete from Application WHERE  Application_id=?";
		int rows = 0;
		try (Connection connection = getConnection(); PreparedStatement pstat = connection.prepareStatement(SQL);) {
			pstat.setInt(1, app_id);

			rows = pstat.executeUpdate();
			System.out.println("Applicant deleted successfully.....");
			logger.info("Deleting Application Details in MACdaoImpl Layer");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
