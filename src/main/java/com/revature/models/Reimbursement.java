package com.revature.models;

public class Reimbursement{
	private int reimb_ID;
	private double amount = 0;
	private String submissionDate;
	private String resolvedDate;
	private String description;
	//private byte[] receipt;
	private int author;
	private int resolver;
	private ReimbursementStatus status = ReimbursementStatus.Pending;
	private int status_ID=0;
	private ReimbursementType type;	
	private int type_ID;
	
	
	public Reimbursement(int reimb_ID, double amount, String submissionDate, String resolvedDate,
						String description, int author, int resolver, int status_ID, int type_ID) {
		super();
		this.reimb_ID = reimb_ID;
		this.amount = amount;
		this.submissionDate = submissionDate;
		this.resolvedDate = resolvedDate;
		this.description = description;
		//this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status_ID = status_ID;
		this.type_ID = type_ID;
	}
	 
	public Reimbursement (ReimbursementEntry entry) {
		super();
		this.amount = entry.getAmount();
		this.description = entry.getDescription();
		this.type_ID = entry.getType_ID();
	}

	public int getReimb_ID() {
		return reimb_ID;
	}

	public void setReimb_ID(int reimb_ID) {
		this.reimb_ID = reimb_ID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}*/

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

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public int getStatus_ID() {
		return status_ID;
	}

	public void setStatus_ID(int status_ID) {
		this.status_ID = status_ID;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	public int getType_ID() {
		return type_ID;
	}

	public void setType_ID(int type_ID) {
		this.type_ID = type_ID;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + author;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		//result = prime * result + Arrays.hashCode(receipt);
		result = prime * result + reimb_ID;
		result = prime * result + ((resolvedDate == null) ? 0 : resolvedDate.hashCode());
		result = prime * result + resolver;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + status_ID;
		result = prime * result + ((submissionDate == null) ? 0 : submissionDate.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + type_ID;
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		/*if (!Arrays.equals(receipt, other.receipt))
			return false;*/
		if (reimb_ID != other.reimb_ID)
			return false;
		if (resolvedDate == null) {
			if (other.resolvedDate != null)
				return false;
		} else if (!resolvedDate.equals(other.resolvedDate))
			return false;
		if (resolver != other.resolver)
			return false;
		if (status != other.status)
			return false;
		if (status_ID != other.status_ID)
			return false;
		if (submissionDate == null) {
			if (other.submissionDate != null)
				return false;
		} else if (!submissionDate.equals(other.submissionDate))
			return false;
		if (type != other.type)
			return false;
		if (type_ID != other.type_ID)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Reimbursement [reimb_ID=" + reimb_ID + ", amount=" + amount + ", submissionDate=" + submissionDate
				+ ", resolvedDate=" + resolvedDate + ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", status=" + status
				+ ", status_ID=" + status_ID + ", type=" + type + ", type_ID=" + type_ID + "]";
	}
	
}
	