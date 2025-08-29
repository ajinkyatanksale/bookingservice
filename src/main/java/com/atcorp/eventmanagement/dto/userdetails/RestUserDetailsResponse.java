package com.atcorp.eventmanagement.dto.userdetails;

import com.atcorp.eventmanagement.dto.FailureResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestUserDetailsResponse {
    private RestUserDetailsSuccessResponse successResponse;
    private FailureResponse failureResponse;
}


