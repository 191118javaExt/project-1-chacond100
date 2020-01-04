package com.revature.ersapplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.User;


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
		boolean addUser(User user) {
		
		try (Connection connection = connect()){
			
			String addUserSql = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, ROLE_ID) VALUES (?,?,?,?,?,?)";
			PreparedStatement addUser = connection.prepareStatement(addUserSql);
			addUser.setString(1, user.getFirstName());
			addUser.setString(2, user.getLastName());
			addUser.setString(3, user.getUsername());
			addUser.setString(4, user.getPassword());
			addUser.setString(5, user.getEmail());
			addUser.setInt(6, user.getRole_ID());
			addUser.execute();
			
			boolean verification = addUser.execute();
			if(verification == false) {
				return true;
			}

		} catch (SQLException e) {
			logger.warn("Failed to create user", e);
			e.printStackTrace();
		}
		return false;
	}
			
		//Create Reimbursement
		public boolean addReimbursement(Reimbursement reimbursement) {
			try (Connection connection = connect()){
				String addReimbursementSql = "INSERT INTO REIMBURSEMENTS (AMOUNT, SUBMISSION_DATE, RESOLVED_DATE, DESCRIPTION, AUTHOR_ID, RESOLVER, STATUS_ID, TYPE) VALUES (?,?,?,?,?,?,?,?);";
				PreparedStatement addReimbursement = connection.prepareStatement(addReimbursementSql);
				addReimbursement.setDouble(1, reimbursement.getAmount());
				addReimbursement.setString(2, reimbursement.getSubmissionDate());
				addReimbursement.setString(3, reimbursement.getResolvedDate());
				addReimbursement.setString(4, reimbursement.getDescription());
				addReimbursement.setInt(5, reimbursement.getAuthor());
				addReimbursement.setInt(6, reimbursement.getResolver());
				addReimbursement.setInt(7, reimbursement.getStatus_ID());
				addReimbursement.setInt(8, reimbursement.getType_ID());
				
				boolean verification = addReimbursement.execute();
				if(verification == false) {
					return true;
				}
				}catch(SQLException ex) {
					logger.warn("Unable to add reimbursement request", ex);
				}
				return false;
				}

		//Update Reimbursement Status
		boolean updateReimbursement(Reimbursement reimbursement) {
			try(Connection connection = connect()){
				String updateReimbStatusSql = "UPDATE REIMBURSEMENTS SET AMOUNT=?, SUBMISSIONS_DATE=?, SUBMISSIONS_DATE=?, DESCRIPTION=?, AUTHOR_ID=?, RESOLVER=?, STATUS_ID=?, TYPE=? WHERE REIMB_ID=?";  
				PreparedStatement updateReimb = connection.prepareStatement(updateReimbStatusSql);
				updateReimb.setDouble(1, reimbursement.getAmount());
				updateReimb.setString(2, reimbursement.getSubmissionDate());
				updateReimb.setString(3, reimbursement.getResolvedDate());
				updateReimb.setString(4, reimbursement.getDescription());
				//updateReimb.setBytes(5, reimbursement.getReceipt());
				updateReimb.setInt(5, reimbursement.getAuthor());
				updateReimb.setInt(6, reimbursement.getResolver());
				updateReimb.setInt(7, reimbursement.getStatus_ID());
				updateReimb.setInt(8, reimbursement.getType_ID());
				boolean verification = updateReimb.execute();
				if (verification == false) {
					return true;
				}
				}catch (SQLException ex) {
					logger.warn("Unable to update reimbursement status", ex);
				}
			return false;	
		}
		
		//Get reimbursements from specific user
		public TreeMap<Integer, Reimbursement> getReimbursements(int user_ID) {

			TreeMap<Integer, Reimbursement> reimbursements = new TreeMap<>();
			try (Connection connection = connect()) {

				String findReimbursementSql = "SELECT * FROM REIMBURSEMENTS WHERE AUTHOR_ID = ?;";
				PreparedStatement findReimbursement = connection.prepareStatement(findReimbursementSql);
				findReimbursement.setInt(1, user_ID);

				ResultSet findReimbursementResults = findReimbursement.executeQuery();
				int i = 1;
				while (findReimbursementResults.next()) {
					int reimb_ID = findReimbursementResults.getInt("REIMB_ID");
					double amount = findReimbursementResults.getDouble("AMOUNT");
					String submissionDate = findReimbursementResults.getString("SUBMISSION_DATE");
					String resolvedDate = findReimbursementResults.getString("RESOLVED_DATE");
					String description = findReimbursementResults.getString("DESCRIPTION");
					//byte[] receipt = findReimbursementResults.getBytes("RECEIPT");
					int author = findReimbursementResults.getInt("AUTHOR_ID");
					int resolver = findReimbursementResults.getInt("RESOLVER");
					int status_ID = findReimbursementResults.getInt("STATUS_ID");
					int type_ID = findReimbursementResults.getInt("TYPE");

					Reimbursement reimbursement = new Reimbursement(reimb_ID, amount, submissionDate, resolvedDate, description, author, resolver, status_ID, type_ID);
					
					reimbursements.put(i, reimbursement);
					i++;
				}
				findReimbursementResults.close();
			} catch (SQLException e) {
				logger.warn("Failed to retrieve reimbursements", e);
				e.printStackTrace();
			}
			return reimbursements;
		}
		
		//Get specific user information
		public User getUser(String username) {
			try(Connection connection = connect()){
			String findUserSql = "SELECT * FROM USERS WHERE USERNAME=?";
			PreparedStatement findUser = connection.prepareStatement(findUserSql);
			findUser.setString(1, username);
			ResultSet findUserResults = findUser.executeQuery();
			if(findUserResults.next()) { 
			int user_ID = findUserResults.getInt("USER_ID");
			String username1 = findUserResults.getString("USERNAME");
			String password = findUserResults.getString("PASSWORD");
			String firstName = findUserResults.getString("FIRST_NAME");
			String lastName = findUserResults.getString("LAST_NAME");
			String email = findUserResults.getString("EMAIL");
			int role_ID = findUserResults.getInt("ROLE_ID");
			
			User user = new User(user_ID, username1, password, firstName, lastName, email, role_ID);
			findUserResults.close();
			return user;
			}
			findUserResults.close();
			} catch (SQLException e) {
				logger.warn("Unable to retrieve User", e);
				e.printStackTrace();
			} 
			return null;
			}
		
		//login method
		String login(int user_ID) {
			
			String together = null;
			try(Connection connection = connect()){
			String findUserSql = "SELECT USERNAME, PASSWORD FROM USERS WHERE USER_ID=?";
			PreparedStatement findUser = connection.prepareStatement(findUserSql);
			findUser.setInt(1, user_ID);
			ResultSet findUserResults = findUser.executeQuery();
			if(findUserResults.next()) { 
			String username = findUserResults.getString("USERNAME");
			String password = findUserResults.getString("PASSWORD");
			together = username+password;
				}
			} catch (SQLException e) {
				logger.warn("Unable to succesfully request login information");
			} 
			return together;
		}
		
		//ending bracket
}
