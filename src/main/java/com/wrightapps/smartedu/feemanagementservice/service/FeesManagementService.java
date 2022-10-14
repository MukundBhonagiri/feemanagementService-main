package com.wrightapps.smartedu.feemanagementservice.service;

import java.util.List;



import com.wrightapps.smartedu.feemanagementservice.entity.FeesTemplate;
import com.wrightapps.smartedu.feemanagementservice.entity.StudentFeeTemplate;
import com.wrightapps.smartedu.feemanagementservice.model.FeeTemplateVo;

public interface FeesManagementService {
	
	List<FeesTemplate> saveFeeManagementDetails(FeeTemplateVo templateVo);
	
	List<StudentFeeTemplate> getFeeManagementDetails(Integer studentId);
	
	void updateFeeManagementDetails(FeeTemplateVo templateVo );
	
	void deleteFeeManagementDetails(Integer feesId);

}
