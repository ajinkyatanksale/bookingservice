package com.atcorp.eventmanagement.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface KafkaService {
    void sendMessage(JsonNode message);
}
