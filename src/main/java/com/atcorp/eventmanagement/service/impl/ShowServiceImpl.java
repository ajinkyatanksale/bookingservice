package com.atcorp.eventmanagement.service.impl;

import com.atcorp.eventmanagement.dao.EventsDao;
import com.atcorp.eventmanagement.dao.ShowsDao;
import com.atcorp.eventmanagement.entities.EventEntity;
import com.atcorp.eventmanagement.entities.ShowEntity;
import com.atcorp.eventmanagement.model.Show;
import com.atcorp.eventmanagement.service.ShowService;
import com.atcorp.eventmanagement.util.enums.ShowStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    EventsDao eventsDao;

    @Autowired
    ShowsDao showsDao;

    @Autowired
    SeatLockService seatLockService;

    @Override
    public long createShow(Show show) {
        List<EventEntity> eventsList = eventsDao.findByEventIdInTimeFrame(show.getEventId(), Timestamp.valueOf(show.getShowTime().lower()), Timestamp.valueOf(show.getShowTime().upper()));

        if (eventsList.isEmpty()) return 0;

        List<ShowEntity> showEntityList = showsDao.findByEventIdInTimeFrame(show.getEventId(), Timestamp.valueOf(show.getShowTime().lower()), Timestamp.valueOf(show.getShowTime().upper()));

        if (!showEntityList.isEmpty()) return 0;

        ShowEntity showEntity = new ShowEntity.ShowEntityBuilder()
                .eventEntity(eventsList.get(0))
                .venueEntity(eventsList.get(0).getVenue())
                .showTime(show.getShowTime())
                .status(show.getStatus().name())
                .bookedSeats("{}")
                .build();

        showsDao.save(showEntity);
        return showEntity.getShowId();
    }

    @Override
    public List<Show> getShowsByEvent(long eventId) throws JsonProcessingException {
        List<ShowEntity> showEntityList = showsDao.findByEventId(eventId);
        List<Show> shows = new ArrayList<>();
        mapShowEntityToShow(showEntityList, shows);
        return shows;
    }

    @Override
    public List<Show> getShowsByEventAndTime(long eventId, Timestamp startTime, Timestamp endTime) throws JsonProcessingException {
        List<ShowEntity> showEntityList = showsDao.findByEventIdInTimeFrame(eventId, startTime, endTime);
        List<Show> shows = new ArrayList<>();
        mapShowEntityToShow(showEntityList, shows);
        return shows;
    }

    private void mapShowEntityToShow(List<ShowEntity> shows, List<Show> showResult) throws JsonProcessingException {
        for (ShowEntity showEntity : shows) {
            Show newShow = new Show.ShowsBuilder()
                    .showId(showEntity.getShowId())
                    .eventId(showEntity.getEventEntity().getEventId())
                    .showTime(showEntity.getShowTime())
                    .status(ShowStatusEnum.valueOf(showEntity.getStatus()))
                    .bookedSeats(new ObjectMapper().readTree(showEntity.getBookedSeats()))
                    .build();
            showResult.add(newShow);
        }
    }
}
