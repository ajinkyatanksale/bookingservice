package com.atcorp.eventmanagement.service.impl;

import com.atcorp.eventmanagement.dto.userdetails.RestUserDetailsResponse;
import com.atcorp.eventmanagement.service.RestClinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientServiceImpl implements RestClinetService {

    @Autowired
    RestTemplate restTemplate;

    public RestUserDetailsResponse getUserDetailsByUserId (long userId) {
        ResponseEntity<RestUserDetailsResponse> response = restTemplate.getForEntity("http://localhost:8081/internal/getUserInfo/" + userId, RestUserDetailsResponse.class);
        return response.getBody();
    }
}
