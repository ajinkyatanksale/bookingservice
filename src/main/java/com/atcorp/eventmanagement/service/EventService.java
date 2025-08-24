package com.atcorp.eventmanagement.service;

import com.atcorp.eventmanagement.model.Event;
import com.vladmihalcea.hibernate.type.range.Range;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    public long createEvent(Event event);
    public List<Event> getEventByNameAndEventTime(String name, Timestamp start, Timestamp end);
    public List<Event> getEventByNameAndVenueAndEventTime(String name, long venueId, Timestamp start, Timestamp end);
}
