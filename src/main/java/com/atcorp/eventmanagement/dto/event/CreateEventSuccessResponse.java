package com.atcorp.eventmanagement.dto.event;

import com.atcorp.eventmanagement.dto.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventSuccessResponse implements SuccessResponse {
    private long eventId;
    private String message;
}
