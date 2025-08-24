package com.atcorp.eventmanagement.dto.seat;

import com.atcorp.eventmanagement.dto.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookSeatsSuccessResponse implements SuccessResponse {
    private String message;
    private String seatBookingStatus;
}
