package com.wrightapps.smartedu.feemanagementservice.port;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wrightapps.smartedu.feemanagementservice.entity.FeesTemplate;
import com.wrightapps.smartedu.feemanagementservice.entity.StudentFeeTemplate;
import com.wrightapps.smartedu.feemanagementservice.model.FeeTemplateVo;



public interface FeesPort {
	
	
	@PostMapping("/setFees")
	public ResponseEntity<List<FeesTemplate>> saveFeeManagementDetails(@Valid @RequestBody FeeTemplateVo templateVo);
	
	@GetMapping("/{studentId}")
	public List<StudentFeeTemplate> getFeeManagementDetails(@PathVariable("studentId") Integer studentId);
	
	@PostMapping
	public ResponseEntity<FeesTemplate> updateFeeManagementDetails(@RequestBody FeeTemplateVo templateVo );
	
	@DeleteMapping("/{feesId}")
	public void deleteFeeManagementDetails(@PathVariable("feesId") Integer feesId);

}
