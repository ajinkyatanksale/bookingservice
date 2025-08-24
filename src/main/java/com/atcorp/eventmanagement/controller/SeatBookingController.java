package com.atcorp.eventmanagement.controller;

import com.atcorp.eventmanagement.dto.FailureResponse;
import com.atcorp.eventmanagement.dto.Response;
import com.atcorp.eventmanagement.dto.seat.BookSeatsRequest;
import com.atcorp.eventmanagement.dto.seat.BookSeatsSuccessResponse;
import com.atcorp.eventmanagement.service.SeatBookingSearvice;
import com.atcorp.eventmanagement.util.enums.FailureEnum;
import com.atcorp.eventmanagement.util.enums.SeatBookingStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/seats")
public class SeatBookingController {

    @Autowired
    SeatBookingSearvice seatBookingService;

    @PostMapping("/lockSeat")
    public ResponseEntity<Response> lockSeats(@Valid @RequestBody BookSeatsRequest bookSeatsRequest) throws JsonProcessingException {
        SeatBookingStatusEnum lockSeatResult = seatBookingService.lockSeats(bookSeatsRequest.getShowId(), bookSeatsRequest.getSeats(), bookSeatsRequest.getUserId());
        Response response = new Response();
        if (lockSeatResult != SeatBookingStatusEnum.LOCKED) {
            FailureResponse failureResponse = new FailureResponse("Seat locking failed!", FailureEnum.valueOf(lockSeatResult.toString()));
            response.setFailureResponse(failureResponse);
        } else {
            BookSeatsSuccessResponse bookSeatsSuccessResponse = new BookSeatsSuccessResponse("Seats locked successfully!", lockSeatResult.name());
            response.setSuccessResponse(bookSeatsSuccessResponse);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/bookSeats")
    public ResponseEntity<Response> bookSeats(@Valid @RequestBody BookSeatsRequest bookSeatsRequest) throws JsonProcessingException {
        SeatBookingStatusEnum bookSeatResult = seatBookingService.bookSeats(bookSeatsRequest.getShowId(), bookSeatsRequest.getSeats(), bookSeatsRequest.getUserId());
        Response response = new Response();
        if (bookSeatResult != SeatBookingStatusEnum.BOOKED) {
            FailureResponse failureResponse = new FailureResponse("Seat booking failed!", FailureEnum.valueOf(bookSeatResult.toString()));
            response.setFailureResponse(failureResponse);
        } else {
            BookSeatsSuccessResponse bookSeatsSuccessResponse = new BookSeatsSuccessResponse("Seats booked successfully!", bookSeatResult.name());
            response.setSuccessResponse(bookSeatsSuccessResponse);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
