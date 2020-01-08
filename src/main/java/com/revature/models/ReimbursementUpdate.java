package com.revature.models;

import java.io.Serializable;

public class ReimbursementUpdate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1955529666342545158L;
	private int reimb_ID;
	private int status_ID;
	
	public ReimbursementUpdate(int reimb_ID, int status_ID) {
		super();
		this.reimb_ID = reimb_ID;
		this.status_ID = status_ID;
	}
	public ReimbursementUpdate() {
		super();
	}
	public int getReimb_ID() {
		return reimb_ID;
	}
	public void setReimb_ID(int reimb_ID) {
		this.reimb_ID = reimb_ID;
	}
	public int getStatus_ID() {
		return status_ID;
	}
	public void setStatus_ID(int status_ID) {
		this.status_ID = status_ID;
	}
	
	
}	

