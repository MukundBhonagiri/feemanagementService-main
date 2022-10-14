package com.wrightapps.smartedu.feemanagementservice.service.impl;

import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

import org.apache.http.entity.StringEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrightapps.smartedu.feemanagementservice.connector.GraphqlConnector;
import com.wrightapps.smartedu.feemanagementservice.entity.StudentFeeTemplate;
import com.wrightapps.smartedu.feemanagementservice.repository.StudentFeeRepository;
import com.wrightapps.smartedu.feemanagementservice.service.KafkaMessageService;

public class EventSourcingServiceImpl implements KafkaMessageService{
	
	@Autowired
	StudentFeeRepository studentFmRepo;

	@Override
	@KafkaListener(topics = "#{'${kafka.feemanagement.topic}'}", containerFactory = "listenerContainerFactory")
	public void onMessage(List<ConsumerRecord<Integer, String>> data) {
		data.parallelStream().forEach(vehicleData -> {
			StudentFeeTemplate studentFeeTemplate = new StudentFeeTemplate();
			StringEntity entity = null;
			try {
				entity = new StringEntity(
				        new ObjectMapper().writeValueAsString(vehicleData), "utf-8");
				entity.setContentType("application/json; charset=utf-8");
			} catch (UnsupportedCharsetException | JsonProcessingException e) {
				e.printStackTrace();
			}
			GraphqlConnector connector = new GraphqlConnector();
			String responseObj = connector.fetchCurrentAssignment(entity);
			
			studentFmRepo.save(studentFeeTemplate);
			//Right the logic to update the student fee details
		});
		
	}

}
