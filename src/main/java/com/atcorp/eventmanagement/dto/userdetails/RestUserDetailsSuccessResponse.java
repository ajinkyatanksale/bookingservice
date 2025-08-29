package com.atcorp.eventmanagement.dto.userdetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestUserDetailsSuccessResponse {
    private String username;
    private String name;
    private String dob;
    private String gender;
    private String phoneNumber;
}

