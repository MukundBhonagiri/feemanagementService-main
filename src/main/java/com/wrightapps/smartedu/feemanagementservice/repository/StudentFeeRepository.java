package com.wrightapps.smartedu.feemanagementservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wrightapps.smartedu.feemanagementservice.entity.StudentFeeTemplate;

public interface StudentFeeRepository extends CrudRepository<StudentFeeTemplate, Integer> {

	List<StudentFeeTemplate> findByFeesId(Integer feesId);

	void deleteByFeesId(Integer feesId);

	List<StudentFeeTemplate> findByStudentId(Integer studentId);

	List<StudentFeeTemplate> findByFeesIdAndPaidAndReceiptNumberAndNotificationSent(Integer feesId, boolean paid,
			String receiptNumber, boolean notificationSent);

}