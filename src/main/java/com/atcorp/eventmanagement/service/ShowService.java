package com.atcorp.eventmanagement.service;

import com.atcorp.eventmanagement.model.Show;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.Timestamp;
import java.util.List;

public interface ShowService {

    long createShow(Show show);

    List<Show> getShowsByEvent(long eventId) throws JsonProcessingException;

    List<Show> getShowsByEventAndTime(long eventId, Timestamp startTime, Timestamp endTime) throws JsonProcessingException;
}
