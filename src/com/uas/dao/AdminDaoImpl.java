package com.uas.dao;

/**
*
* University Admission System
* 
* Admin DAO Implementation Class
* This is the DAO layer for the Admin architecture,
* with implementations of methods for showing applicants details, adding, deleting or viewing offered/scheduled programs.
* 
* 
*
* @version 1.0
* @since   2019-10-29
*/

import java.sql.Connection;
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
import com.uas.beans.Program;
import com.uas.beans.ProgramScheduled;
import com.uas.exception.ApplicationException;
import com.uas.service.AdminServiceImpl;
import com.uas.service.ApplicationService;
import com.uas.service.ApplicationServiceImpl;
import com.uas.util.ConnectionFactory;
import com.uas.validation.ProgramValidation;

public class AdminDaoImpl implements AdminDao {
	public AdminDaoImpl() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	final Logger logger = Logger.getLogger(AdminDaoImpl.class);

	ApplicationService appServ = new ApplicationServiceImpl();
	ConnectionFactory conFactory = ConnectionFactory.getInstance();
	ProgramValidation validation = new ProgramValidation();

	@Override
	public void showApplicantsDetails() {
		Application app = new Application();
		List<Application> lists = new ArrayList<Application>();
		String SQL = "SELECT * FROM Application ORDER BY Application_id ";
		try (Connection connection = conFactory.getConnection(); Statement stat = connection.createStatement();) {
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
				logger.info("Listing Applicant Details in AdminDaoImpl Layer");
				if (lists.size() > 0)
					System.out.println(lists);
				else {
					logger.info("Applications Not Found");
					throw new ApplicationException("Applications Not Found");
				}

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public List<Program> showDetails() {
		List<Program> list1 = new ArrayList<Program>();

		String queryString = "SELECT * FROM PROGRAMS_OFFERED";

		try (Connection conn = conFactory.getConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				Program prog = new Program();
				prog.setProgram(rs.getString("PROGRAMNAME"));
				prog.setDescription(rs.getString("DESCRIPTION"));
				prog.setEligibility(rs.getString("APPLICANT_ELIGIBILITY"));
				prog.setDuration(Integer.parseInt(rs.getString("DURATION")));
				prog.setDegree(rs.getString("DEGREE_CERTIFICATE_OFFERED"));
				list1.add(prog);
				logger.info("Listing Offered Programs Details in AdminDaoImpl Layer");
			}
		} catch (SQLException e) {
			System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
		}

		return list1;
	}

	public List<ProgramScheduled> showScheduledDetails() {
		String queryString = "SELECT * FROM PROGRAMS_SCHEDULED";
		List<ProgramScheduled> list = new ArrayList<ProgramScheduled>();

		try (Connection conn = conFactory.getConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				ProgramScheduled prog = new ProgramScheduled();

				prog.setProgramId(rs.getString("SCHEDULED_PROGRAM_ID"));
				prog.setProgramName(rs.getString("PROGRAMNAME"));
				prog.setLocation(rs.getString("LOCATION"));
				prog.setStartDate(rs.getString("START_DATE"));
				prog.setEndDate(rs.getString("END_DATE"));
				prog.setSessionsPerWeek(rs.getInt("SESSIONS_PER_WEEK"));
				list.add(prog);
				logger.info("Listing Scheduled Programs Details in AdminDaoImpl Layer");
			}
		} catch (SQLException e) {
			System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
		}

		return list;
	}

	public void adddetails(Program prg) {
		System.out.println("Press 1 to offer new program");
		System.out.println("Press 2 to schedule offered program");
		Scanner sc = new Scanner(System.in);

		int choice = sc.nextInt();
		if (choice == 1) {
			System.out.println("--------New Program Form---------\n");
			System.out.println("Enter program name: ");
			String input = sc.next();
			while (!validation.validateProgram(input)) {
				System.out.println("Enter VALID program name: ");
				input = sc.next();
			}
			prg.setProgram(input);
			System.out.println("Enter program description: ");
			prg.setDescription(sc.next());
			System.out.println("Choose candidate eligibility from : ");
			System.out.println("1.XII");
			System.out.println("2.B.TECH");
			System.out.println("3.M.TECH");
			System.out.println("select choice....");
			int c = sc.nextInt();
			switch (c) {
			case 1:
				prg.setEligibility("XII");
				System.out.println("Selected choice XII");
				break;
			case 2:
				prg.setEligibility("B.TECH");
				System.out.println("Selected choice B.TECH");
				break;
			case 3:
				prg.setEligibility("M.TECH");
				System.out.println("Selected choice M.TECH");
				break;
			default:
				System.out.println("invalid choice");
				break;
			}

			System.out.println("Enter program duration(years): ");
			int duration = sc.nextInt();

			prg.setDuration(duration);
			System.out.println("Enter degree certificate offered: ");
			input = sc.next();
			while (!validation.validateDegree(input)) {
				System.out.println("Enter VALID  degree certificate offered: ");
				input = sc.next();
			}
			prg.setDegree(input);

			String queryString = "INSERT INTO PROGRAMS_OFFERED( ProgramName,description,applicant_eligibility, duration , degree_certificate_offered )VALUES(?,?,?,?,?) ";

			try (Connection conn = conFactory.getConnection();
					PreparedStatement ptmt = conn.prepareStatement(queryString);) {
				ptmt.setString(1, prg.getProgram());
				ptmt.setString(2, prg.getDescription());
				ptmt.setString(3, prg.getEligibility());
				ptmt.setInt(4, prg.getDuration());
				ptmt.setString(5, prg.getDegree());
				int rows = ptmt.executeUpdate();
				logger.info("New Program Offered in AdminDaoImpl Layer");
				System.out.println(rows + "Program INSERTED successfully....");

			} catch (SQLException e) {
				System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
			}
			System.out.println("Do you want to schedule this program");
			System.out.println("Press y to continue");
			String schedule = null;
			schedule = sc.next();
			if (schedule.equals("y")) {
				addDetailsScheduled(prg.getProgram());
			}

		}
		if (choice == 2) {
			System.out.println("List of offered Programs available \n");

			List<Program> list1 = new ArrayList<Program>();
			list1 = appServ.showDetails();
			Iterator<Program> itr = list1.iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());

			}
			System.out.println("Enter program Name");
			String programName = sc.next();
			addDetailsScheduled(programName);
		}

	}

	public void addDetailsScheduled(String programName) {

		String queryString = "INSERT INTO PROGRAMS_SCHEDULED( Scheduled_program_id,ProgramName,Location , start_date , end_date, sessions_per_week )VALUES(?,?,?,?,?,?) ";
		Scanner sc = new Scanner(System.in);
		try (Connection conn = conFactory.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(queryString);) {
			System.out.println("Enter scheduled program id:");
			ptmt.setString(1, sc.next());
			ptmt.setString(2, programName);
			System.out.println("Enter program location:");
			ptmt.setString(3, sc.next());
			System.out.println("Enter scheduled program's start date: FORMAT :DD/MM/YYYY");
			ptmt.setString(4, sc.next());
			System.out.println("Enter scheduled program's end date:  FORMAT :DD/MM/YYYY");
			ptmt.setString(5, sc.next());
			System.out.println("Enter number of sessions per week:");
			ptmt.setInt(6, sc.nextInt());
			int rows = ptmt.executeUpdate();
			System.out.println(rows + "Program SCHEDULED successfully....");
			logger.info("New Program Scheduled in AdminDaoImpl Layer");
		} catch (SQLException e) {
			System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
		}

	}

	public void deleteDetails(String p) {
		String queryString = "DELETE FROM PROGRAMS_OFFERED WHERE ProgramName=?";
		try (Connection conn = conFactory.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(queryString);) {
			ptmt.setString(1, p);
			System.out.println(p);
			int rows = ptmt.executeUpdate();
			System.out.println(rows + "Program DELETED successfully....");
			logger.info("Offered Program Deleted in AdminDaoImpl Layer");
		} catch (SQLException e) {
			System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
		}
	}

	public void deleteScheduledDetails(String p) {
		String queryString = "DELETE FROM PROGRAMS_SCHEDULED WHERE ProgramName=?";
		try (Connection conn = conFactory.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(queryString);) {
			ptmt.setString(1, p);
			System.out.println("Deleting " + p);
			int rows = ptmt.executeUpdate();
			System.out.println("Scheduled Program DELETED successfully....");
			logger.info("Scheduled Program Deleted in AdminDaoImpl Layer");
		} catch (SQLException e) {
			System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
		}
	}

}
