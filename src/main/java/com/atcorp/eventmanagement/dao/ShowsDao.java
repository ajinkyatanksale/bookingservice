package com.atcorp.eventmanagement.dao;

import com.atcorp.eventmanagement.entities.ShowEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ShowsDao extends CrudRepository<ShowEntity, Long> {

    @Query(value="select * from event_management.Show as s where s.event_id = :eventId", nativeQuery = true)
    List<ShowEntity> findByEventId(@Param("eventId") long eventId);

    @Query(value = "select * from event_management.Show as s where s.event_id =:eventId and s.show_time <@ tsrange(:start, :end)", nativeQuery = true)
    List<ShowEntity> findByEventIdInTimeFrame(@Param("eventId") long eventId, @Param("start") Timestamp start, @Param("end") Timestamp end);

}
