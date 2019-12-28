package com.revature.main;

import org.apache.log4j.Logger;

import ersapplication.Employee;
import ersapplication.System;

public class Driver {
	private static Logger logger = Logger.getLogger(Driver.class);
	public static void main(String[] args) {
		System system = new System();
		Employee employee = system.getReimbursement(1);
	}
}
