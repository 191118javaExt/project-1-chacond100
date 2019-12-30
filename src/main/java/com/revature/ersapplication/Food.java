package com.revature.ersapplication;

import java.sql.Blob;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class Food extends Reimbursement{
		
		Food(int reimb_ID, int amount, LocalDateTime submissionDate, LocalDateTime resolvedDate, String description, Blob receipt, int author,int resolver,int status_ID){
			super(reimb_ID);
			this.setAmount(amount);
			this.setSubmissionDate(submissionDate);
			this.setResolvedDate(resolvedDate);
			this.setDescription(description);
			this.setReciept(receipt);
			this.setAuthor(author);
			this.setResolver(resolver);
			this.setStatus_ID(status_ID);
			}
		
		@Override
		public ReimbursementType getReimbursementType() {
			return ReimbursementType.Food;
			}
	}
