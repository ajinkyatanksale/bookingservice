package com.atcorp.eventmanagement.service;

import com.atcorp.eventmanagement.dto.userdetails.RestUserDetailsResponse;

public interface RestClinetService {
    public RestUserDetailsResponse getUserDetailsByUserId (long userId);
}
