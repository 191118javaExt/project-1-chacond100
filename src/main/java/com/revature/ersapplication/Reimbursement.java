package com.revature.ersapplication;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public abstract class Reimbursement implements Serializable {
	private int reimbursementNumber;
	private int amount = 0;
	private LocalDateTime submissionDate;
	private LocalDateTime resolvedDate;
	private String description;
	private Blob reciept;
	private int author;
	private int resolver;
	private int status_ID;
	private int type_ID;
	
	Reimbursement (int reimb_ID){
		reimbursementNumber= reimb_ID;
	}

	public abstract ReimbursementType getReimbursementType();
	
	public int getReimbursementNumber() {
		return reimbursementNumber;
	}

	void setReimbursementNumber(int reimbursementNumber) {
		this.reimbursementNumber = reimbursementNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReciept() {
		return reciept;
	}

	public void setReciept(Blob reciept) {
		this.reciept = reciept;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus_ID() {
		return status_ID;
	}

	public void setStatus_ID(int status_ID) {
		this.status_ID = status_ID;
	}

	public int getType_ID() {
		return type_ID;
	}

	public void setType_ID(int type_ID) {
		this.type_ID = type_ID;
	}

	public LocalDateTime getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDateTime submissionDate) {
		this.submissionDate = submissionDate;
	}

	public LocalDateTime getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(LocalDateTime resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementNumber=" + reimbursementNumber + ", amount=" + amount + ", submissionDate="
				+ submissionDate + ", resolvedDate=" + resolvedDate + ", description=" + description + ", reciept="
				+ reciept + ", author=" + author + ", resolver=" + resolver + ", status_ID=" + status_ID + ", type_ID="
				+ type_ID + "]";
	}

	}
