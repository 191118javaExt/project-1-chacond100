package com.revature.main;

import java.util.ArrayList;

import com.revature.ersapplication.Application;
import com.revature.ersapplication.Employee;

public class Driver {
	public static void main(String[] args) {
		
		Application application = new Application();
		ArrayList<Employee> allReimbursements = application.getAllReimbursements();
		System.out.println(allReimbursements);
	}
}
