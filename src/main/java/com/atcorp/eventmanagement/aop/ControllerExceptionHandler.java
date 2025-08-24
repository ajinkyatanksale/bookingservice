package com.atcorp.eventmanagement.aop;

import com.atcorp.eventmanagement.dto.FailureResponse;
import com.atcorp.eventmanagement.dto.Response;
import com.atcorp.eventmanagement.util.enums.FailureEnum;
import com.atcorp.eventmanagement.util.exceptions.SeatBookingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.w3c.dom.ranges.RangeException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Response> handleJsonProcessingException() {
        Response response = new Response();
        FailureResponse failureResponse = new FailureResponse("Request not in proper format", FailureEnum.MALFORMED_REQUEST);
        response.setFailureResponse(failureResponse);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RangeException.class)
    public ResponseEntity<Response> handleRangeException() {
        Response response = new Response();
        FailureResponse failureResponse = new FailureResponse("Improper date range provided", FailureEnum.MALFORMED_REQUEST);
        response.setFailureResponse(failureResponse);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatBookingException.class)
    public ResponseEntity<Response> handleSeatBookingException() {
        Response response = new Response();
        FailureResponse failureResponse = new FailureResponse("Seat booking failed. You will receive your refund. Sorry for the inconvenience.", FailureEnum.SEAT_BOOKING_FAILED);
        response.setFailureResponse(failureResponse);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
