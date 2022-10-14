package com.wrightapps.smartedu.feemanagementservice.service;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaMessageService {
	
    void onMessage(List<ConsumerRecord<Integer, String>> data);


}
