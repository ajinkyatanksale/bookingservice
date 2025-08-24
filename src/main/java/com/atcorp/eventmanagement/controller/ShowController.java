package com.atcorp.eventmanagement.controller;

import com.atcorp.eventmanagement.dto.FailureResponse;
import com.atcorp.eventmanagement.dto.Response;
import com.atcorp.eventmanagement.dto.show.CreateShowRequest;
import com.atcorp.eventmanagement.dto.show.CreateShowSuccessResponse;
import com.atcorp.eventmanagement.dto.show.GetShowsSuccessResponse;
import com.atcorp.eventmanagement.model.Show;
import com.atcorp.eventmanagement.service.ShowService;
import com.atcorp.eventmanagement.util.enums.FailureEnum;
import com.atcorp.eventmanagement.util.enums.ShowStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manage/shows")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/create")
    public ResponseEntity<Response> createShow(@Valid @RequestBody CreateShowRequest createShowRequest) {
        Show show = new Show.ShowsBuilder()
                .eventId(createShowRequest.getEventId())
                .showTime(createShowRequest.getShowTime())
                .status(ShowStatusEnum.valueOf(createShowRequest.getStatus()))
                .build();

        long showId = showService.createShow(show);
        Response response = new Response();
        if (showId == 0) {
            FailureResponse failureResponse = new FailureResponse("Show creation failed", FailureEnum.SHOW_CREATION_FAILED);
            response.setFailureResponse(failureResponse);
        } else {
            CreateShowSuccessResponse createShowSuccessResponse = new CreateShowSuccessResponse(showId, "New show created successgully!");
            response.setSuccessResponse(createShowSuccessResponse);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getShowByEvent")
    public ResponseEntity<Response> getShowByEvent(@RequestParam("eventId") long eventId) throws JsonProcessingException {
        List<Show> shows = showService.getShowsByEvent(eventId);
        List<com.atcorp.eventmanagement.dto.show.Show> showResult = new ArrayList<>();
        mapShowObject(shows, showResult);

        GetShowsSuccessResponse getShowsSuccessResponse = new GetShowsSuccessResponse(showResult);
        Response response = new Response();
        response.setSuccessResponse(getShowsSuccessResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getShowByEventAndTime")
    public ResponseEntity<Response> getShowByEventAndTime(@RequestParam("eventId") long eventId, @RequestParam("startTime") Timestamp start, @RequestParam("endTime") Timestamp end) throws JsonProcessingException {
        List<Show> shows = showService.getShowsByEventAndTime(eventId, start, end);
        List<com.atcorp.eventmanagement.dto.show.Show> showResult = new ArrayList<>();
        mapShowObject(shows, showResult);

        GetShowsSuccessResponse getShowsSuccessResponse = new GetShowsSuccessResponse(showResult);
        Response response = new Response();
        response.setSuccessResponse(getShowsSuccessResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void mapShowObject(List<Show> shows, List<com.atcorp.eventmanagement.dto.show.Show> showResult) {
        for (Show show : shows) {
            com.atcorp.eventmanagement.dto.show.Show newShow = new com.atcorp.eventmanagement.dto.show.Show(show.getShowId(),
                    show.getEventId(), show.getShowTime(), show.getStatus().name(), show.getBookedSeats());
            showResult.add(newShow);
        }
    }
}
