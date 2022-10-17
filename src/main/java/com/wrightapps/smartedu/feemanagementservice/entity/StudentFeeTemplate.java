package com.wrightapps.smartedu.feemanagementservice.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "student_feepayment_details")
@Data
public class StudentFeeTemplate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="academic_year")
	@NotNull
	private String academicYear;
	@Column(name = "fees_id")
    private Integer feesId;
	@Column(name = "fees_category")
    private String feesCategory;
	@Column(name = "to_pay")
    private double toPay;
	@Column(name= "student_id")
    private UUID studentId;
	@Column(name= "pay_term")
    private String payTerm;
	@Column(name = "due_date")
    private String dueDate;
	@Column(name = "paid")
    private boolean paid;
	@Column(name = "paid_date")
    private Date paidDate;
	@Column(name = "payment_mode")
    private String paymentMode;
	@Column(name = "receipt_number")
    private String receiptNumber;
	@Column(name = "reference_no")
    private String referenceNo;
	@Column(name="notification_sent")
    private boolean notificationSent;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public Integer getFeesId() {
		return feesId;
	}
	public void setFeesId(Integer feesId) {
		this.feesId = feesId;
	}
	public String getFeesCategory() {
		return feesCategory;
	}
	public void setFeesCategory(String feesCategory) {
		this.feesCategory = feesCategory;
	}
	public double getToPay() {
		return toPay;
	}
	public void setToPay(double toPay) {
		this.toPay = toPay;
	}
	public UUID getStudentId() {
		return studentId;
	}
	public void setStudentId(UUID studentId) {
		this.studentId = studentId;
	}
	public String getPayTerm() {
		return payTerm;
	}
	public void setPayTerm(String payTerm) {
		this.payTerm = payTerm;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public boolean isNotificationSent() {
		return notificationSent;
	}
	public void setNotificationSent(boolean notificationSent) {
		this.notificationSent = notificationSent;
	}

	
}
