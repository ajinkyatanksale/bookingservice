package com.atcorp.eventmanagement.service.impl;

import com.atcorp.eventmanagement.service.KafkaService;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.kafka.support.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(JsonNode message) {
        CompletableFuture<SendResult<String, String>> result =  kafkaTemplate.send("notification.requests.v1", message.toString());
        result.isDone();
    }
}
