package com.atcorp.eventmanagement.dao;

import com.atcorp.eventmanagement.entities.UserSeatMapEntity;
import com.atcorp.eventmanagement.entities.UserShowSeatId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSeatMapDao extends CrudRepository<UserSeatMapEntity, UserShowSeatId> {
    @Query(value = "select * from event_management.user_seat_map usm where usm.user_id = :user_id", nativeQuery = true)
    List<UserSeatMapEntity> findByUserId(@Param("user_id") long userId);
}
