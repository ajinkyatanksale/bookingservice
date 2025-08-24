package com.atcorp.eventmanagement.service.impl;

import com.atcorp.eventmanagement.dao.VenuesDao;
import com.atcorp.eventmanagement.entities.VenueEntity;
import com.atcorp.eventmanagement.model.Venue;
import com.atcorp.eventmanagement.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenuesDao venuesDao;

    @Override
    public long createVenue(Venue venue) {
        VenueEntity venueEntity = new VenueEntity.VenueEntityBuilder()
                .venueName(venue.getVenueName())
                .city(venue.getCity())
                .pinCode(venue.getPinCode())
                .sectionsJson(venue.getSectionsJson())
                .build();
        venuesDao.save(venueEntity);

        return venueEntity.getVenueId();
    }

    @Override
    public List<Venue> getAllVenuesByPinCode(String pinCode) {
        List<VenueEntity> venues = venuesDao.findVenueByPinCode(pinCode);
        List<Venue> venuesResult = new ArrayList<>();
        mapVenueEntityToVenue(venues, venuesResult);
        return venuesResult;
    }

    @Override
    public List<Venue> getVenueByVenueName(String venueName) {
        List<VenueEntity> venues = venuesDao.findVenueByVenueName("%" + venueName + "%");
        List<Venue> venuesResult = new ArrayList<>();
        mapVenueEntityToVenue(venues, venuesResult);
        return venuesResult;
    }

    private void mapVenueEntityToVenue(List<VenueEntity> venues, List<Venue> venuesResult) {
        for (VenueEntity venue : venues) {
            Venue newVenue = new Venue.VenuesBuilder().venue_id(venue.getVenueId()).venueName(venue.getVenueName()).pinCode(venue.getPinCode()).city(venue.getCity()).sectionsJson(venue.getSectionsJson()).build();
            venuesResult.add(newVenue);
        }
    }
}
