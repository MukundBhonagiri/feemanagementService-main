package com.wrightapps.smartedu.feemanagementservice.service.impl;

import java.nio.charset.UnsupportedCharsetException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.entity.StringEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrightapps.smartedu.feemanagementservice.connector.GraphqlConnector;
import com.wrightapps.smartedu.feemanagementservice.entity.FeesTemplate;
import com.wrightapps.smartedu.feemanagementservice.entity.StudentFeeTemplate;
import com.wrightapps.smartedu.feemanagementservice.repository.StudentFeeRepository;
import com.wrightapps.smartedu.feemanagementservice.service.KafkaMessageService;

public class EventSourcingServiceImpl implements KafkaMessageService{
	
	@Autowired
	StudentFeeRepository studentFmRepo;
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	@KafkaListener(topics = "#{'${kafka.feemanagement.topic}'}", containerFactory = "listenerContainerFactory")
	public void onMessage(List<ConsumerRecord<Integer, String>> data) {
		data.parallelStream().forEach(feeTemplate -> {
			StudentFeeTemplate studentFeeTemplate = new StudentFeeTemplate();
			StringEntity entity = null;
			try {
				entity = new StringEntity(
				        new ObjectMapper().writeValueAsString(feeTemplate), "utf-8");
				entity.setContentType("application/json; charset=utf-8");
			} catch (UnsupportedCharsetException | JsonProcessingException e) {
				e.printStackTrace();
			}
			GraphqlConnector connector = new GraphqlConnector();
			String responseObj = connector.fetchCurrentAssignment(entity);
					
			FeesTemplate feesTemplate = mapper.convertValue(feeTemplate, FeesTemplate.class);
			studentFeeTemplate = convertTemplatetoStudentTemplate(feesTemplate, studentFeeTemplate);
			UUID studentId = UUID.fromString(responseObj);
			
			studentFeeTemplate.setStudentId(studentId);
			studentFmRepo.save(studentFeeTemplate);
			//Right the logic to update the student fee details
		});
	}
	
	private StudentFeeTemplate convertTemplatetoStudentTemplate(FeesTemplate feesTemplate, StudentFeeTemplate studentFeeTemplate) {
		
		String academicYear = LocalDate.now().getYear() + "-" + (LocalDate.now().getYear() + 1);
		studentFeeTemplate.setAcademicYear(academicYear);
		studentFeeTemplate.setFeesId(feesTemplate.getFeesId());
		studentFeeTemplate.setFeesCategory(feesTemplate.getFeesCategory());
		studentFeeTemplate.setPaid(false);
		studentFeeTemplate.setPaidDate(new Date());
		studentFeeTemplate.setPaymentMode(null);
		studentFeeTemplate.setReceiptNumber(null);
		studentFeeTemplate.setReferenceNo(null);
		studentFeeTemplate.setNotificationSent(false);
		studentFeeTemplate.setPayTerm(feesTemplate.getTerm());
		studentFeeTemplate.setDueDate(feesTemplate.getDueDate());
		studentFeeTemplate.setToPay(feesTemplate.getToPay());
		
		return studentFeeTemplate;
	}

}
