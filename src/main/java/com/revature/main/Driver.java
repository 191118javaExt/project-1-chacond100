package com.revature.main;

import com.revature.ersapplication.Application;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementEntry;
import com.revature.models.User;

public class Driver {
	public static void main(String[] args) {
			Application application = new Application();
			ReimbursementEntry input = new ReimbursementEntry(1000, "Example 2", 200);
			boolean newReimb = application.addReimbursement(input, 2);
			System.out.println(newReimb);
	}
}