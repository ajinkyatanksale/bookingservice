package com.atcorp.eventmanagement.service.impl;

import com.atcorp.eventmanagement.dao.UserSeatMapDao;
import com.atcorp.eventmanagement.entities.ShowEntity;
import com.atcorp.eventmanagement.entities.UserSeatMapEntity;
import com.atcorp.eventmanagement.model.UserBookingsDetails;
import com.atcorp.eventmanagement.model.Venue;
import com.atcorp.eventmanagement.service.UserBookingDetailsService;
import com.atcorp.eventmanagement.util.enums.CategoryEnum;
import com.atcorp.eventmanagement.util.enums.ShowStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserBookingDetailsServiceImpl implements UserBookingDetailsService {

    @Autowired
    UserSeatMapDao userSeatMapDao;

    private final Logger logger = LoggerFactory.getLogger(UserBookingDetailsServiceImpl.class);

    @Override
    public UserBookingsDetails getUserBookingDetails(long userId) {

        List<UserSeatMapEntity> userSeatMapEntityList = userSeatMapDao.findByUserId(userId);

        HashMap<ShowEntity, List<String>> map = new HashMap<>();

        userSeatMapEntityList.forEach(userSeatMapEntity -> {
            map.putIfAbsent(userSeatMapEntity.getShow(), new ArrayList<>());
            map.get(userSeatMapEntity.getShow()).add(userSeatMapEntity.getUserShowSeatId().getSeatId());
        });


        Map<Long, UserBookingsDetails.Events> eventsMap = new HashMap<>();

        map.forEach((key, value) -> {
            UserBookingsDetails.Show show = new UserBookingsDetails.Show();
            show.setShowId(key.getShowId());
            show.setBookedSeats(value);
            show.setShowTime(key.getShowTime());
            show.setShowStatusEnum(ShowStatusEnum.valueOf(key.getStatus()));
            if (!eventsMap.containsKey(key.getEventEntity().getEventId())) {
                UserBookingsDetails.Events events = new UserBookingsDetails.Events();
                events.setEventId(key.getEventEntity().getEventId());
                events.setEventTime(key.getEventEntity().getEventTime());
                events.setEventName(key.getEventEntity().getEventName());
                events.setCategory(CategoryEnum.valueOf(key.getEventEntity().getCategory()));
                events.setDescription(key.getEventEntity().getDescription());
                Venue venue = new Venue.VenuesBuilder()
                        .venue_id(key.getVenueEntity().getVenueId())
                        .venueName(key.getVenueEntity().getVenueName())
                        .city(key.getVenueEntity().getCity())
                        .pinCode(key.getVenueEntity().getPinCode())
                        .sectionsJson(key.getVenueEntity().getSectionsJson())
                        .build();
                events.setVenue(venue);
                events.setShowList(new ArrayList<>());
                events.getShowList().add(show);
                eventsMap.put(events.getEventId(), events);
            } else {
                eventsMap.get(key.getEventEntity().getEventId()).getShowList().add(show);
            }
        });

        return new UserBookingsDetails.UserBookingsDetailsBuilder()
                .userId(userId)
                .eventsList(eventsMap.values().stream().toList())
                .build();
    }
}
