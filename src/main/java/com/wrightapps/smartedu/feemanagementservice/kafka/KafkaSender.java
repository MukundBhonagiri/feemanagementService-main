package com.wrightapps.smartedu.feemanagementservice.kafka;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
    private static Logger log = LoggerFactory.getLogger(KafkaSender.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String key, String data) {
        try {
            log.debug("Sending ({}) to {}...", data, topic);
            SendResult<String, String> result = kafkaTemplate.send(topic, key, data).get();
            log.debug("Topic = {}, Partition = {}, Offset = {}", result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Execution Exception Sending Record", e);
        }
    }
}
