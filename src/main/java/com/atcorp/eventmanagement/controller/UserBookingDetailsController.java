package com.atcorp.eventmanagement.controller;

import com.atcorp.eventmanagement.dto.Response;
import com.atcorp.eventmanagement.dto.userdetails.GetUserDetailsSuccessResponse;
import com.atcorp.eventmanagement.model.UserBookingsDetails;
import com.atcorp.eventmanagement.service.UserBookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserBookingDetailsController {

    @Autowired
    UserBookingDetailsService userBookingDetailsService;

    @GetMapping("/details")
    public ResponseEntity<Response> getUserBookingDetails(@RequestParam("userId") long userId) {
        UserBookingsDetails userBookingsDetails = userBookingDetailsService.getUserBookingDetails(userId);
        GetUserDetailsSuccessResponse getUserDetailsSuccessResponse = new GetUserDetailsSuccessResponse(userBookingsDetails.getUserId(), userBookingsDetails.getEventsList());
        Response response = new Response();
        response.setSuccessResponse(getUserDetailsSuccessResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
