package com.wrightapps.smartedu.feemanagementservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.wrightapps.smartedu.feemanagementservice.entity.FeesTemplate;
import com.wrightapps.smartedu.feemanagementservice.entity.StudentFeeTemplate;
import com.wrightapps.smartedu.feemanagementservice.model.FeeTemplateVo;
import com.wrightapps.smartedu.feemanagementservice.port.FeesPort;
import com.wrightapps.smartedu.feemanagementservice.service.FeesManagementService;

@RestController
@RequestMapping("/api/v1/feesmanagement")
public class FeesManagementController implements FeesPort{
	
	private static final Logger log = LogManager.getLogger(FeesManagementController.class);
	
	private FeesManagementService feesManagementService;
	
	@Autowired
	public FeesManagementController(FeesManagementService feesManagementService) {
		this.feesManagementService = feesManagementService;
	}
	

	@Override
	public ResponseEntity<List<FeesTemplate>> saveFeeManagementDetails(@Valid FeeTemplateVo templateVo) {
		log.info("entered to controller"+templateVo.getTenantId());
		List<FeesTemplate> feeTemp = feesManagementService.saveFeeManagementDetails(templateVo);
		return new ResponseEntity<List<FeesTemplate>>(feeTemp, HttpStatus.CREATED);
	}

	@Override
	public List<StudentFeeTemplate> getFeeManagementDetails(Integer studentId) {
		return feesManagementService.getFeeManagementDetails(studentId);
	}

	@Override
	public ResponseEntity<FeesTemplate> updateFeeManagementDetails(FeeTemplateVo templateVo) {
		FeesTemplate feeTemp = new FeesTemplate();
		  feesManagementService.updateFeeManagementDetails(templateVo);
		return  new ResponseEntity<FeesTemplate>(feeTemp, HttpStatus.CREATED);
	}

	@Override
	public void deleteFeeManagementDetails(Integer feesId) {
		feesManagementService.deleteFeeManagementDetails(feesId);

		
	}

}
