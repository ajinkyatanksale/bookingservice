package com.atcorp.eventmanagement.dao;

import com.atcorp.eventmanagement.entities.EventEntity;
import com.atcorp.eventmanagement.entities.VenueEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.sql.Timestamp;
import java.util.List;

public interface EventsDao extends CrudRepository<EventEntity, Long> {
    List<EventEntity> findByEventName(String eventName);

    List<EventEntity> findByEventNameAndVenue(String eventName, VenueEntity venue);

    @Query(value = "select * from event_management.Event as e where e.event_name =:eventName and e.event_time && tsrange(:start, :end)", nativeQuery = true)
    List<EventEntity> findByEventNameInTimeFrame(@Param("eventName") String eventName, @Param("start") Timestamp start, @Param("end") Timestamp end);

    @Query(value = "select * from event_management.Event as e where e.event_name =:eventName and venue_id =:venueId and e.event_time && tsrange(:start, :end)", nativeQuery = true)
    List<EventEntity> findByEventNameAndVenueInTimeFrame(String eventName, long venueId, Timestamp start, Timestamp end);

    @Query(value = "select * from event_management.Event as e where e.event_id =:eventId and e.event_time @> tsrange(:start, :end, '[]')", nativeQuery = true)
    List<EventEntity> findByEventIdInTimeFrame(@Param("eventId") long eventId, @Param("start") Timestamp start, @Param("end") Timestamp end);
}
