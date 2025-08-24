package com.atcorp.eventmanagement.service;

import com.atcorp.eventmanagement.model.UserBookingsDetails;

public interface UserBookingDetailsService {
    UserBookingsDetails getUserBookingDetails(long userId);
}
