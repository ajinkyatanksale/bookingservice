package com.atcorp.eventmanagement.dao;

import com.atcorp.eventmanagement.entities.VenueEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VenuesDao extends CrudRepository<VenueEntity, Long> {

    VenueEntity findVenueByVenueId(long venueId);

    List<VenueEntity> findVenueByPinCode(String pinCode);

    @Query("SELECT e FROM VenueEntity e WHERE e.venueName Like :venueName")
    List<VenueEntity> findVenueByVenueName(@Param("venueName") String venueName);
}
