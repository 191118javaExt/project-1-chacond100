package com.revature.ersapplication;

public class Employee {
	private final Reimbursement reimbursement;
	
	Employee(Reimbursement reimbursement){
		this.reimbursement = reimbursement;
	}

	@Override
	public String toString() {
		return "Employee [reimbursement=" + reimbursement + "]";
	}
	
	Reimbursement getReimbursement() {
		return reimbursement;
	}
}
