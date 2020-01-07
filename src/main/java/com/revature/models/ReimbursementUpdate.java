package com.revature.models;

public class ReimbursementUpdate {
	
	private int reimb_ID;
	private int status_ID;
	private int resolver;
	
	
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
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimb_ID;
		result = prime * result + resolver;
		result = prime * result + status_ID;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementUpdate other = (ReimbursementUpdate) obj;
		if (reimb_ID != other.reimb_ID)
			return false;
		if (resolver != other.resolver)
			return false;
		if (status_ID != other.status_ID)
			return false;
		return true;
	}
	
	public ReimbursementUpdate(int reimb_ID, int status_ID, int resolver) {
		super();
		this.reimb_ID = reimb_ID;
		this.status_ID = status_ID;
		this.resolver = resolver;
	}
	
		
}
