package com.atcorp.eventmanagement.service;

import com.atcorp.eventmanagement.model.Venue;
import java.util.List;

public interface VenueService {
    long createVenue(Venue venue);
    List<Venue> getAllVenuesByPinCode(String pinCode);
    List<Venue> getVenueByVenueName(String venueName);
}
