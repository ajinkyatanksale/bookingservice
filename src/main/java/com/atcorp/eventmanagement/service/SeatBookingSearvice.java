package com.atcorp.eventmanagement.service;

import com.atcorp.eventmanagement.util.enums.SeatBookingStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface SeatBookingSearvice {
    SeatBookingStatusEnum lockSeats(long showId, List<String> seatId, long userId) throws JsonProcessingException;

    SeatBookingStatusEnum bookSeats(long showId, List<String> seatId, long userId) throws JsonProcessingException;
}
