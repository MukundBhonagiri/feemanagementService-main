package com.wrightapps.smartedu.feemanagementservice.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;

@Entity
@Table(name = "fees_template")
@Data
public class FeesTemplate implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feesId;
	
	@Column(name= "tenant_id")
	private UUID tenantId;
	@Column(name= "school_id")
	private UUID schoolId;
	@Column(name = "grade_ids")
	private String gradeIds;
	@Column(name= "fees_category")
	private String feesCategory;
	@Column(name = "fees_description")
	private String feesDescription;
	@Column(name = "term")
	private String term;
	@Column(name= "term_id")
	private UUID termId;
	@Column(name="due_date")
	private String dueDate;
	@Column(name= "to_pay")
	private double toPay;
	@Column(name="notify_before")
	private Integer notifyBefore;
	@Column(name="pay_frequency")
	private String payFrequency;
	@Column(name="applicable_to")
	private String applicableTo;
	@Column(name = "created_by")
	private UUID createdBy;
	@Column(name="updated_by")
	private String updatedBy;
	@Column(name="allow_partial_pay")
	private boolean allowPartialPay;
	@Column(name="validity")
	private String validity;
	@Column(name="currency")
	private String currency;
	@Column(name="is_active")
	private boolean isActive;
	
	public Integer getFeesId() {
		return feesId;
	}
	public void setFeesId(Integer feesId) {
		this.feesId = feesId;
	}
	public UUID getTenantId() {
		return tenantId;
	}
	public void setTenantId(UUID tenantId) {
		this.tenantId = tenantId;
	}
	public UUID getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(UUID schoolId) {
		this.schoolId = schoolId;
	}
	public String getGradeIds() {
		return gradeIds;
	}
	public void setGradeIds(String gradeIds) {
		this.gradeIds = gradeIds;
	}
	public String getFeesCategory() {
		return feesCategory;
	}
	public void setFeesCategory(String feesCategory) {
		this.feesCategory = feesCategory;
	}
	public String getFeesDescription() {
		return feesDescription;
	}
	public void setFeesDescription(String feesDescription) {
		this.feesDescription = feesDescription;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public UUID getTermId() {
		return termId;
	}
	public void setTermId(UUID termId) {
		this.termId = termId;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public double getToPay() {
		return toPay;
	}
	public void setToPay(double toPay) {
		this.toPay = toPay;
	}
	public Integer getNotifyBefore() {
		return notifyBefore;
	}
	public void setNotifyBefore(Integer notifyBefore) {
		this.notifyBefore = notifyBefore;
	}
	public String getPayFrequency() {
		return payFrequency;
	}
	public void setPayFrequency(String payFrequency) {
		this.payFrequency = payFrequency;
	}
	public String getApplicableTo() {
		return applicableTo;
	}
	public void setApplicableTo(String applicableTo) {
		this.applicableTo = applicableTo;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public boolean isAllowPartialPay() {
		return allowPartialPay;
	}
	public void setAllowPartialPay(boolean allowPartialPay) {
		this.allowPartialPay = allowPartialPay;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public UUID getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}
	
	
		
}
