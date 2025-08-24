package com.atcorp.eventmanagement.controller;


import com.atcorp.eventmanagement.dto.Response;
import com.atcorp.eventmanagement.dto.venue.CreateVenueRequest;
import com.atcorp.eventmanagement.dto.venue.CreateVenueSuccess;
import com.atcorp.eventmanagement.dto.venue.GetVenuesSuccessResponse;
import com.atcorp.eventmanagement.model.Venue;
import com.atcorp.eventmanagement.service.VenueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manage/venue")
public class VenueController {

    @Autowired
    VenueService venueService;

    @PostMapping("/create")
    public ResponseEntity<Response> createVenue(@Valid @RequestBody CreateVenueRequest createVenueRequest) {
        Venue venue = new Venue.VenuesBuilder()
                .venueName(createVenueRequest.getVenueName())
                .city(createVenueRequest.getCity())
                .sectionsJson(createVenueRequest.getSectionsJson().toString())
                .pinCode(createVenueRequest.getPinCode())
                .build();
        long venueId = venueService.createVenue(venue);
        CreateVenueSuccess createVenueSuccess = new CreateVenueSuccess(Long.toString(venueId), "Venue Created Successfully!");
        Response response = new Response();
        response.setSuccessResponse(createVenueSuccess);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getVenuesByPinCode")
    public ResponseEntity<Response> getAllVenues(@RequestParam("pincode") String pinCode) throws JsonProcessingException {
        List<Venue> venues = venueService.getAllVenuesByPinCode(pinCode);
        List<com.atcorp.eventmanagement.dto.venue.Venue> venueResult = new ArrayList<>();
        mapVenueObject(venues, venueResult);
        GetVenuesSuccessResponse getVenuesSuccessResponse = new GetVenuesSuccessResponse(venueResult);
        Response response = new Response();
        response.setSuccessResponse(getVenuesSuccessResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getVenuesByName")
    public ResponseEntity<Response> getVenueByVenueName(@RequestParam("name") String venueName) throws JsonProcessingException {
        List<Venue> venues = venueService.getVenueByVenueName(venueName);
        List<com.atcorp.eventmanagement.dto.venue.Venue> venueResult = new ArrayList<>();
        mapVenueObject(venues, venueResult);
        GetVenuesSuccessResponse getVenuesSuccessResponse = new GetVenuesSuccessResponse(venueResult);
        Response response = new Response();
        response.setSuccessResponse(getVenuesSuccessResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void mapVenueObject(List<Venue> venues, List<com.atcorp.eventmanagement.dto.venue.Venue> venuesResult) throws JsonProcessingException {
        for (Venue venue : venues) {
            com.atcorp.eventmanagement.dto.venue.Venue newVenue = new com.atcorp.eventmanagement.dto.venue.Venue(venue.getVenueId(), venue.getVenueName(), venue.getCity(), new ObjectMapper().readTree(venue.getSectionsJson()), venue.getPinCode());
            venuesResult.add(newVenue);
        }
    }
}
