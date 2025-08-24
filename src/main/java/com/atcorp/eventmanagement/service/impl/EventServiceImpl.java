package com.atcorp.eventmanagement.service.impl;

import com.atcorp.eventmanagement.dao.EventsDao;
import com.atcorp.eventmanagement.dao.VenuesDao;
import com.atcorp.eventmanagement.entities.EventEntity;
import com.atcorp.eventmanagement.entities.VenueEntity;
import com.atcorp.eventmanagement.model.Event;
import com.atcorp.eventmanagement.service.EventService;
import com.atcorp.eventmanagement.util.enums.CategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventsDao eventsDao;

    @Autowired
    VenuesDao venuesDao;

    @Override
    public long createEvent(Event event) {
        List<Event> events = getEventByNameAndVenueAndEventTime(event.getEventName(), event.getVenueId(), Timestamp.valueOf(event.getEventTime().lower()), Timestamp.valueOf(event.getEventTime().upper()));
        VenueEntity venueEntity = venuesDao.findVenueByVenueId(event.getVenueId());

        if (events.isEmpty() && Objects.nonNull(venueEntity)) {
            EventEntity eventEntity = new EventEntity.EventEntityBuilder()
                    .eventName(event.getEventName())
                    .description(event.getDescription())
                    .category(event.getCategory().name())
                    .venue(venueEntity)
                    .eventTime(event.getEventTime())
                    .build();
            eventsDao.save(eventEntity);
            return eventEntity.getEventId();
        } else {
            return 0;
        }
    }

    @Override
    public List<Event> getEventByNameAndEventTime(String name, Timestamp start, Timestamp end) {
        List<Event> events = new ArrayList<>();
        List<EventEntity> availableEvents = eventsDao.findByEventNameInTimeFrame(name, start, end);
        mapEventEntityToEvent(availableEvents, events);
        return events;
    }

    @Override
    public List<Event> getEventByNameAndVenueAndEventTime(String name, long venueId, Timestamp start, Timestamp end) {
        List<Event> events = new ArrayList<>();
        List<EventEntity> availableEvents = eventsDao.findByEventNameAndVenueInTimeFrame(name, venueId, start, end);
        mapEventEntityToEvent(availableEvents, events);
        return events;
    }

    private void mapEventEntityToEvent(List<EventEntity> events, List<Event> eventResult) {
        for (EventEntity event : events) {
            Event newEvent = new Event.EventsBuilder()
                    .eventId(event.getEventId())
                    .eventName(event.getEventName())
                    .venueId(event.getVenue().getVenueId())
                    .category(CategoryEnum.valueOf(event.getCategory()))
                    .description(event.getDescription())
                    .eventTime(event.getEventTime())
                    .build();
            eventResult.add(newEvent);
        }
    }
}
