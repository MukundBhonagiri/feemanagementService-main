package com.wrightapps.smartedu.feemanagementservice.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FeeTemplateVo {
	
	private Integer feesId;
	@Valid
	@NotEmpty(message = "Tenant id required")
	@NotNull(message = "Tenant id required")
	private String tenantId;
	@NotNull(message = "School Id required")
	private String schoolId;
	@NotNull(message = "Graph Ids required")
	private String gradeIds;
	@NotNull(message="Fees Category required")
	private String feesCategory;
	private String feesDescription;
	private ArrayList<FeePayDetails> payTerm = new ArrayList<>();
	@NotNull(message = " Applicable to is required")
	private String applicableTo;
	private Integer createdBy;
	private String updatedBy;
	private boolean allowPartialPay;
	private LocalDate validity;
	private String currency;
	@NotNull(message = "is Active required")
	private boolean isActive;
	@NotNull(message = "Notify Before is required")
	private Integer notifyBefore;
	
		
	public Integer getFeesId() {
		return feesId;
	}
	public void setFeesId(Integer feesId) {
		this.feesId = feesId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
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
	public List<FeePayDetails> getPayTerm() {
		return payTerm;
	}
	public void setPayTerm(ArrayList<FeePayDetails> payTerm) {
		this.payTerm = payTerm;
	}
	public String getApplicableTo() {
		return applicableTo;
	}
	public void setApplicableTo(String applicableTo) {
		this.applicableTo = applicableTo;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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
	public LocalDate getValidity() {
		return validity;
	}
	public void setValidity(LocalDate validity) {
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
	public Integer getNotifyBefore() {
		return notifyBefore;
	}
	public void setNotifyBefore(Integer notifyBefore) {
		this.notifyBefore = notifyBefore;
	}
	
	
}
