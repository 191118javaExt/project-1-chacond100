package com.revature.ersapplication;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import org.apache.log4j.Logger;


@SuppressWarnings("serial")
public class Application implements Serializable {
	
	final static Logger logger = Logger.getLogger(Application.class);
	
	private DBService database = new DBService();
	
	public int addUser(String first_name, String last_name, String username, String password, String email, int role_ID) {
		return database.addUser(first_name, last_name, username, password, email, role_ID);
	}
	
	public int addReimbursement(int amount, String description, Blob receipt, int author, int resolver, int status_ID, ReimbursementType reimbursementType){
		return database.addReimbursement(amount, description, receipt, author, resolver, status_ID, reimbursementType);
	}

	public Employee getReimbursement(int reimb_ID) {
		return database.getReimbursement(reimb_ID);
	}
	
	public ArrayList<Employee> getAllReimbursements(){
		return database.getAllReimbursements();
	}
	
	public int updateReimbStatus (int reimb_ID, int status_ID, int author) {
		@SuppressWarnings("unused")
		Employee employee = getReimbursement(reimb_ID);
		database.updateReimbStatus(reimb_ID, status_ID, author);
		return status_ID;
	}

	public String login(int user_ID) {
		String userPassword = database.login(user_ID);
		return userPassword;
	}

}
