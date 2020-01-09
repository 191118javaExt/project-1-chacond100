package com.revature.ersapplication;

import static org.junit.Assert.*;

import java.util.List;
import java.util.TreeMap;

import org.junit.Test;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementEntry;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Roles;
import com.revature.models.UserEntry;

public class ApplicationTest {

	int reimb_ID = 9;
	double amount = 1000;
	String submissionDate;
	String resolvedDate;
	String description = "Test Description";
	byte[] receipt;
	int author = 2;
	int resolver = 1;
	ReimbursementStatus status = ReimbursementStatus.Pending;
	int status_ID=0;
	ReimbursementType type;	
	int type_ID=100;
	
	String username = "username";
	String password = "password";
	String firstName = "Pancho";
	String lastName = "Villa";
	String email = "pancho@yahoo.com";
	int role_ID =1;
	
	DBService database = new DBService();
	
	Application application = new Application();
	
	Reimbursement reimbursement = new Reimbursement(reimb_ID, amount,submissionDate, resolvedDate,
			 description,  author,  resolver, status_ID,  type_ID,receipt);
	
	ReimbursementEntry input = new ReimbursementEntry(amount, description, type_ID);
	
	UserEntry entry = new UserEntry(username, password, firstName, lastName, email, role_ID);
	
	@Test
	public void validReimbursementTest () {
		assertTrue(application.validReimbursement(reimbursement));
	}
	
	@Test
	public void conversionStatus_IDtoEnumTest() {
		ReimbursementStatus conversion = application.conversionStatus_IDtoEnum(0);
		assertEquals(ReimbursementStatus.Pending, conversion );
	}
	
	@Test
	public void conversionEnumtoStatus_IDTest() {
		int conversion = application.conversionEnumToStatus_ID(ReimbursementStatus.Pending);
		assertEquals(0, conversion );
	}
	
	@Test
	public void conversionType_IDtoEnumTest() {
		ReimbursementType conversion = application.conversionTypeIDtoEnum(100);
		assertEquals(ReimbursementType.Lodging, conversion );
	}
	
	@Test
	public void conversionEnumtoType_IDTest() {
		int conversion = application.conversionEnumtoTypeID(ReimbursementType.Lodging);
		assertEquals(100, conversion );
	}
	
	@Test
	public void conversionRole_IDtoEnumTest() {
		Roles conversion = application.conversionRoleIDtoEnum(100);
		assertEquals(Roles.Employee, conversion );
	}
	
	@Test
	public void addReimbursementTest() {
		assertTrue(application.addReimbursement(input, author));
	}
	
	@Test
	public void updateReimbursementTest() {
		assertTrue(application.updateReimbursement(reimb_ID, status_ID, resolver));
	}
	
	@Test
	public void addUserTest() {
		assertTrue(application.addUser(entry));
	}
	
	@Test
	public void getReimbursementByIDTest() {
		List<Reimbursement> response = application.getReimbursementByID(2);
		assertNotNull(response);
	}
	
	@Test
	public void getReimbursementsTest() {
		TreeMap<Integer, Reimbursement> response2 = application.getReimbursements(2);
		assertNotNull(response2);
	}
}

