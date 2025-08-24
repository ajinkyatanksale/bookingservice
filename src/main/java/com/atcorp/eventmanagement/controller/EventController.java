package com.atcorp.eventmanagement.controller;

import com.atcorp.eventmanagement.dto.FailureResponse;
import com.atcorp.eventmanagement.dto.Response;
import com.atcorp.eventmanagement.dto.event.CreateEventRequest;
import com.atcorp.eventmanagement.dto.event.CreateEventSuccessResponse;
import com.atcorp.eventmanagement.dto.event.GetEventSuccessResponse;
import com.atcorp.eventmanagement.model.Event;
import com.atcorp.eventmanagement.service.EventService;
import com.atcorp.eventmanagement.util.enums.FailureEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/manage/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<Response> createEvent(@Valid @RequestBody CreateEventRequest createEventRequest) {
        Response response = new Response();
        Event event = new Event.EventsBuilder()
                .eventName(createEventRequest.getEventName())
                .description(createEventRequest.getDescription())
                .category(createEventRequest.getCategory())
                .venueId(createEventRequest.getVenueId())
                .eventTime(createEventRequest.getEventTime())
                .build();

        long eventId = eventService.createEvent(event);
        if (eventId > 0) {
            CreateEventSuccessResponse createEventSuccessResponse = new CreateEventSuccessResponse(eventId, "New Event created successfully!");
            response.setSuccessResponse(createEventSuccessResponse);
        } else {
            FailureResponse failureResponse = new FailureResponse("Venue already booked for another event in this time frame", FailureEnum.VENUE_ALREADY_BOOKED);
            response.setFailureResponse(failureResponse);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getByNameAndTime")
    public ResponseEntity<Response> getEventByNameAndTime(@RequestParam("name") String eventName,
                                                          @RequestParam("start") String start,
                                                          @RequestParam("end") String end) throws JsonProcessingException, ParseException {

        String formatPattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
        Date parsedStartTime = dateFormat.parse(start);
        Date parsedEndTime = dateFormat.parse(end);

        List<Event> events = eventService.getEventByNameAndEventTime(eventName, new Timestamp(parsedStartTime.getTime()), new Timestamp(parsedEndTime.getTime()));
        List<com.atcorp.eventmanagement.dto.event.Event> eventResult = new ArrayList<>();
        mapEventObject(events, eventResult);
        GetEventSuccessResponse getEventSuccessResponse = new GetEventSuccessResponse(eventResult);
        Response response = new Response();
        response.setSuccessResponse(getEventSuccessResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getByNameVenueAndTime")
    public ResponseEntity<Response> getEventByNameVenueAndTime(@RequestParam("name") String eventName,
                                                          @RequestParam("venue") long venueId,
                                                          @RequestParam("start") String start,
                                                          @RequestParam("end") String end) throws JsonProcessingException, ParseException {

        String formatPattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
        Date parsedStartTime = dateFormat.parse(start);
        Date parsedEndTime = dateFormat.parse(end);

        List<Event> events = eventService.getEventByNameAndVenueAndEventTime(eventName, venueId, new Timestamp(parsedStartTime.getTime()), new Timestamp(parsedEndTime.getTime()));
        List<com.atcorp.eventmanagement.dto.event.Event> eventResult = new ArrayList<>();
        mapEventObject(events, eventResult);
        GetEventSuccessResponse getEventSuccessResponse = new GetEventSuccessResponse(eventResult);
        Response response = new Response();
        response.setSuccessResponse(getEventSuccessResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void mapEventObject(List<Event> events, List<com.atcorp.eventmanagement.dto.event.Event> eventResult) throws JsonProcessingException {
        for (Event event : events) {
            com.atcorp.eventmanagement.dto.event.Event newEvent = new com.atcorp.eventmanagement.dto.event.Event(event.getEventId(), event.getEventName(), event.getDescription(), event.getVenueId(), event.getCategory(), event.getEventTime());
            eventResult.add(newEvent);
        }
    }
}
