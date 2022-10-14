package com.wrightapps.smartedu.feemanagementservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.wrightapps.smartedu.feemanagementservice.entity.FeesTemplate;


public interface FeesManagementRepository extends CrudRepository<FeesTemplate, Integer> {

	FeesTemplate findByFeesId(Integer feesId);

}
