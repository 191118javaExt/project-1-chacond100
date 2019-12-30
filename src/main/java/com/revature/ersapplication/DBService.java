package com.revature.ersapplication;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;


public class DBService {

		private static Logger logger = Logger.getLogger(DBService.class);
		
		//Connection
		public Connection connect() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Properties properties = new Properties();

		// search for files in the current project
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Connection connection = null;
		try {
			properties.load(loader.getResourceAsStream("connection.properties"));
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				logger.warn("Unable to obtain connection to database", e);
			}
		} catch (IOException e1) {
		}
		return connection;
	}
		
		//Create User
		int addUser(String first_name, String last_name, String username, String password, String email, int role_ID) {
		int user_ID=-1;
		
		try (Connection connection = connect()){
			connection.setAutoCommit(false);
			
			String addUserSql = "INSERT INTO \"ERS\".USERS (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, ROLE_ID) VALUES (?,?,?,?,?,?)";
			PreparedStatement addUser = connection.prepareStatement(addUserSql, Statement.RETURN_GENERATED_KEYS);
			addUser.setString(1, first_name);
			addUser.setString(2, last_name);
			addUser.setString(3, username);
			addUser.setString(4, password);
			addUser.setString(5, email);
			addUser.setInt(6, role_ID);
			addUser.executeUpdate();
			ResultSet addUserResults = addUser.getGeneratedKeys();
			if(addUserResults.next()) {
				user_ID = addUserResults.getInt(1);
			}
		}catch(SQLException ex) {
			logger.warn("Unable to add user account", ex);
		}
		return user_ID;
		}
		
		//Create Reimbursement
		int addReimbursement(int amount, String description, Blob receipt, int author, int resolver, int status_ID, ReimbursementType reimbursementType) {
			int reimb_ID=-1;
			try (Connection connection = connect()){
				connection.setAutoCommit(false);
				
				String addReimbursementSql = "INSERT INTO \"ERS\".REIMBURSEMENTS (AMOUNT, DESCRIPTION, RECEIPT, AUTHOR, RESOLVER, STATUS_ID, TYPE) VALUES (?,?,?,?,?,?,?)";
				PreparedStatement addReimbursement = connection.prepareStatement(addReimbursementSql, Statement.RETURN_GENERATED_KEYS);
				addReimbursement.setInt(1, amount);
				addReimbursement.setString(2, description);
				addReimbursement.setBlob(3, receipt);
				addReimbursement.setInt(4, author);
				addReimbursement.setInt(5, resolver);
				addReimbursement.setInt(6, status_ID);
				addReimbursement.setString(7, reimbursementType.name());
				addReimbursement.executeUpdate();
				ResultSet addReimbursementResults = addReimbursement.getGeneratedKeys();
				if(addReimbursementResults.next()) {
					reimb_ID = addReimbursementResults.getInt(1);
				}
			}catch(SQLException ex) {
				logger.warn("Unable to add reimbursement request", ex);
			}
			return reimb_ID;
			}

		//Update Reimbursement Status
		boolean updateReimbStatus(int status_ID, int author, int reimb_ID) {
			boolean success = false;
			try(Connection connection = connect()){
				String updateReimbStatusSql = "UPDATE \"ERS\".REIMBURSEMENTS SET STATUS_ID = ?, AUTHOR=? WHERE REIMB_ID = ?";  
				PreparedStatement updateReimb = connection.prepareStatement(updateReimbStatusSql);
				updateReimb.setInt(1, status_ID);
				updateReimb.setInt(2, author);
				updateReimb.setInt(3, reimb_ID);
				updateReimb.executeUpdate();
				success=true;
				}catch (SQLException ex) {
					logger.warn("Unable to update reimbursement status", ex);
				}
			return success;	
		}
		
		//Get all reimbursements for all Users
		ArrayList<Employee> getAllReimbursements(){
			ArrayList<Employee> employees = new ArrayList<>();
			Connection connection = connect ();
			try {
				String findAllEmployeesSql = "SELECT * FROM \"ERS\".REIMBURSEMENTS";
				PreparedStatement findAllEmployees = connection.prepareStatement(findAllEmployeesSql);
				ResultSet findEmployeeResults = findAllEmployees.executeQuery();
				while(findEmployeeResults.next()) {
					ReimbursementType reimbursementType = ReimbursementType.valueOf(findEmployeeResults.getString("TYPE"));
					int reimb_ID = findEmployeeResults.getInt("REIMB_ID");
					int amount = findEmployeeResults.getInt("AMOUNT");
					LocalDateTime submissionDate = findEmployeeResults.getTimestamp("SUBMISSION_DATE").toLocalDateTime();
					LocalDateTime resolvedDate = findEmployeeResults.getTimestamp("RESOLVED_DATE").toLocalDateTime();
					String description = findEmployeeResults.getString("DESCRIPTION");
					Blob receipt = findEmployeeResults.getBlob("RECEIPT");
					int author = findEmployeeResults.getInt("AUTHOR");
					int resolver = findEmployeeResults.getInt("RESOLVED_ID");
					int status_ID = findEmployeeResults.getInt("STATUS_ID");
					
					Reimbursement reimbursement;
					if(reimbursementType == ReimbursementType.Lodging) {
						reimbursement = new Lodging(reimb_ID,amount, submissionDate, resolvedDate, description,receipt,author,resolver,status_ID);
					}else if (reimbursementType == ReimbursementType.Food){
						reimbursement = new Food(reimb_ID,amount,submissionDate, resolvedDate, description,receipt,author,resolver,status_ID);
					}else if (reimbursementType == ReimbursementType.Travel){
						reimbursement = new Travel(reimb_ID,amount, submissionDate, resolvedDate,description,receipt,author,resolver,status_ID);
					}else if (reimbursementType == ReimbursementType.Other){
						reimbursement = new Other(reimb_ID,amount, submissionDate, resolvedDate, description,receipt,author,resolver,status_ID);
					}else {
						throw new Exception("Unknown account type");
					}					
					employees.add(new Employee(reimbursement));
					}
				}catch(Exception e) {
					e.printStackTrace();
					}
			return employees;
		}
		
		//Get reimbursements from specific user
		Employee getReimbursement(int reimb_ID) {
			Employee employee = null;
			try(Connection connection = connect()){
				String findReimbSql="SELECT * FROM \"ERS\".REIMBURSEMENTS WHERE REIMBURSEMENTS.REIMB_ID=?";
				PreparedStatement findReimb = connection.prepareStatement(findReimbSql);
				findReimb.setInt(1, reimb_ID);
				ResultSet findReimbResults = findReimb.executeQuery();
				if(findReimbResults.next()) {
					
					ReimbursementType reimbursementType = ReimbursementType.valueOf(findReimbResults.getString("TYPE"));
					int amount = findReimbResults.getInt("AMOUNT");
					LocalDateTime submissionDate = findReimbResults.getTimestamp("SUBMISSION_DATE").toLocalDateTime();
					LocalDateTime resolvedDate = findReimbResults.getTimestamp("RESOLVED_DATE").toLocalDateTime();
					String description = findReimbResults.getString("DESCRIPTION");
					Blob receipt = findReimbResults.getBlob("RECEIPT");
					int author = findReimbResults.getInt("AUTHOR");
					int resolver = findReimbResults.getInt("RESOLVER");
					int status_ID = findReimbResults.getInt("STATUS_ID");
					Reimbursement reimbursement;
					if(reimbursementType == ReimbursementType.Lodging) {
						reimbursement = new Lodging(reimb_ID, amount, submissionDate, resolvedDate, description,receipt,author,resolver,status_ID);
					}else if (reimbursementType == ReimbursementType.Food){
						reimbursement = new Food(reimb_ID, amount, submissionDate, resolvedDate, description,receipt,author,resolver,status_ID);
					}else if (reimbursementType == ReimbursementType.Travel){
						reimbursement = new Travel(reimb_ID, amount, submissionDate, resolvedDate, description,receipt,author,resolver,status_ID);
					}else if (reimbursementType == ReimbursementType.Other){
						reimbursement = new Other(reimb_ID, amount, submissionDate, resolvedDate, description,receipt,author,resolver,status_ID);
					}else {
						throw new Exception("Unknown account type");
					}					
						employee = new Employee(reimbursement);
					}
				else {
					logger.warn("No reimbursement transaction was found for Reimbursement ID "+ reimb_ID);
					}	
				findReimbResults.close();
				}catch(Exception e) {
						logger.warn("Unable to retrieve information", e);
					}
				return employee;
		}

		String getUser(int USER_ID) {
			String userInformation = null;
			try(Connection connection = connect()){
			String findUserSql = "SELECT * FROM \"ERS\".Users WHERE Users.USER_ID=?";
			PreparedStatement findUser = connection.prepareStatement(findUserSql);
			findUser.setInt(1, USER_ID);
			ResultSet findUserResults = findUser.executeQuery();
			if(findUserResults.next()) { 
			String username = findUserResults.getString("USERNAME");
			String password = findUserResults.getString("PASSWORD");
			String firstName = findUserResults.getString("FIRST_NAME");
			String lastName = findUserResults.getString("LAST_NAME");
			String email = findUserResults.getString("EMAIL");
			int role_ID = findUserResults.getInt("ROLE_ID");
			
			userInformation = "First name: " + firstName+ " Last name: " +lastName+ " Username: "+username+" Password: "+password+ " Email: "+email+" Role ID: "+role_ID;
				}
			} catch (SQLException e) {
				logger.warn("Unable to retrieve User", e);
				e.printStackTrace();
			} 
			return userInformation;
			}

		String login(int user_ID) {
			
			String together = null;
			try(Connection connection = connect()){
			String findUserSql = "SELECT USERNAME, PASSWORD FROM \"ERS\".USERS WHERE USER_ID=?";
			PreparedStatement findUser = connection.prepareStatement(findUserSql);
			findUser.setInt(1, user_ID);
			ResultSet findUserResults = findUser.executeQuery();
			if(findUserResults.next()) { 
			String username = findUserResults.getString("USERNAME");
			String password = findUserResults.getString("PASSWORD");
			together = username+password;
				}
			} catch (SQLException e) {
				logger.warn("Unable to succesfully log in");
			} 
			return together;
		}
		
		//ending bracket
}
