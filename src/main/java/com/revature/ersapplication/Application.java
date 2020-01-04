package com.revature.ersapplication;

import java.io.Serializable;
//import java.sql.Blob;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementEntry;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Roles;
import com.revature.models.User;
import com.revature.models.UserEntry;


@SuppressWarnings("serial")
public class Application implements Serializable {
	
	final static Logger logger = Logger.getLogger(Application.class);
	
	private DBService database = new DBService();
	
	public boolean validReimbursement(Reimbursement reimbursement) {
		double amount = reimbursement.getAmount();
		if(amount >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String addSubmissionDate() {
		Date date = new Date();
		String dateFormat = "yyyy, MM-d h:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}
	
	public ReimbursementStatus conversionStatus_IDtoEnum(int status_ID) {
		ReimbursementStatus status;
		if(status_ID == 0) {
			status = ReimbursementStatus.Pending;
			return status;
		} else if(status_ID == 1) {
			status = ReimbursementStatus.Approved;
			return status;
		} else if(status_ID == -1) {
			status = ReimbursementStatus.Denied;
			return status;
		}
		return null;
	}
	
	public int conversionEnumToStatus_ID(ReimbursementStatus status_ID) {
		int status;
		if(status_ID == ReimbursementStatus.Pending) {
			status = 0;
			return status;
		} else if(status_ID == ReimbursementStatus.Approved) {
			status = 1;
			return status;
		} else if(status_ID == ReimbursementStatus.Denied) {
			status = -1;
			return status;
		}
		else return status=11111;
	}

	public ReimbursementType conversionTypeIDtoEnum(int type_ID) {
		ReimbursementType type;
		if(type_ID == 100) {
			type = ReimbursementType.Lodging;
			return type;
		} else if(type_ID == 200) {
			type = ReimbursementType.Travel;
			return type;
		} else if(type_ID == 300) {
			type = ReimbursementType.Food;
			return type;
		} else if(type_ID == 400) {
			type = ReimbursementType.Other;
			return type;
		}
		return null;
	}
	
	public int conversionEnumtoTypeID(ReimbursementType type_ID) {
		int type;
		if(type_ID == ReimbursementType.Lodging) {
			type = 100;
			return type;
		} else if (type_ID == ReimbursementType.Travel) {
			type = 200;
			return type;
		} else if (type_ID == ReimbursementType.Food) {
			type = 300;
			return type;
		} else if (type_ID == ReimbursementType.Other) {
			type = 400;
			return type;
		}
		return 0;
	}
	
	public Roles conversionRoleIDtoEnum(int role_ID) {
		Roles role;
		if(role_ID == 100) {
			role = Roles.Employee;
			return role;
		} else if(role_ID == 200) {
			role = Roles.FinanceManager;
			return role;
		}
		return null;
	}
	
	public TreeMap<Integer, Reimbursement> getReimbursements(int user_ID) {
		TreeMap<Integer, Reimbursement> reimbursements = database.getReimbursements(user_ID);
		for(int i = 1; i <= reimbursements.size(); i++) {
			reimbursements.get(i).setStatus(conversionStatus_IDtoEnum(reimbursements.get(i).getStatus_ID()));
			reimbursements.get(i).setType(conversionTypeIDtoEnum(reimbursements.get(i).getType_ID()));
		}
		return reimbursements;
	}
	
	public boolean addReimbursement(ReimbursementEntry input, int author){
		Reimbursement newReimbursement = new Reimbursement(input);
		newReimbursement.setAuthor(author);
		if(validReimbursement(newReimbursement)) {
			newReimbursement.setSubmissionDate(addSubmissionDate());
		boolean success = database.addReimbursement(newReimbursement);
		return success;
		}else {
			return false;
		}
	}
	
	public boolean addUser(UserEntry entry) {
		User newUser = new User(entry);
		boolean success = database.addUser(newUser);
		return success;
	}
	
	public User getUser(String username, String password) {
		User user = database.getUser(username);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				TreeMap<Integer, Reimbursement> reimbursements = database.getReimbursements(user.getUser_ID());
				user.setReimbursements(reimbursements);
				user.setRole(conversionRoleIDtoEnum(user.getRole_ID()));
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public User login(String username, String password) {
		//password = DigestUtils.sha256Hex(password);
		User user = getUser(username, password);
		return user;
	}
	
	/*
	public int addUser(String first_name, String last_name, String username, String password, String email, int role_ID) {
		return database.addUser(first_name, last_name, username, password, email, role_ID);
	}
	
	
		public String getUser(int user_ID) {
		return database.getUser(user_ID);
	}
	
	public int updateReimbStatus (int reimb_ID, int status_ID, int author) {
		@SuppressWarnings("unused")
		Employee employee = getReimbursement(reimb_ID);
		database.updateReimbStatus(reimb_ID, status_ID, author);
		return status_ID;
	}

	public boolean login(int user_ID, String username, String password) {
		String userPassword = database.login(user_ID);
		String verification = username+password;
		if(userPassword.equals(verification)) {
			System.out.println("succesful login");
			return true;
		}else {
			System.out.println("Failed to succesfully login");
			return false;
		}
	}*/
}
