package com.revature.models;

public class ReimbursementEntry {
	
	private double amount;
	private String description;
	private int author;
	private int type_ID;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getType_ID() {
		return type_ID;
	}
	public void setType_ID(int type_ID) {
		this.type_ID = type_ID;
	}
	public ReimbursementEntry(double amount, String description, int type_ID) {
		super();
		this.amount = amount;
		this.description = description;
		this.type_ID = type_ID;
	}
		
}
