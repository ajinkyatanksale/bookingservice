package com.atcorp.eventmanagement.dto.show;

import com.atcorp.eventmanagement.dto.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateShowSuccessResponse implements SuccessResponse {
    private long showId;
    private String message;
}
