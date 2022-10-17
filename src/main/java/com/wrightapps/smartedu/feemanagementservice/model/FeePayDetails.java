package com.wrightapps.smartedu.feemanagementservice.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FeePayDetails {

	private String term;
	private String termId;
	private LocalDate dueDate;
	private double toPay;
	private String payFrequency;
	
	public FeePayDetails() {
		
	}
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public double getToPay() {
		return toPay;
	}
	public void setToPay(double toPay) {
		this.toPay = toPay;
	}
	public String getPayFrequency() {
		return payFrequency;
	}
	public void setPayFrequency(String payFrequency) {
		this.payFrequency = payFrequency;
	}
	
	
	
}

