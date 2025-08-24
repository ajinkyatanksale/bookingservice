package com.atcorp.eventmanagement.service.impl;

import com.atcorp.eventmanagement.dao.ShowsDao;
import com.atcorp.eventmanagement.dao.UserSeatMapDao;
import com.atcorp.eventmanagement.entities.ShowEntity;
import com.atcorp.eventmanagement.entities.UserSeatMapEntity;
import com.atcorp.eventmanagement.entities.UserShowSeatId;
import com.atcorp.eventmanagement.service.SeatBookingSearvice;
import com.atcorp.eventmanagement.util.enums.SeatBookingStatusEnum;
import com.atcorp.eventmanagement.util.exceptions.SeatBookingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class SeatBookingServiceImpl implements SeatBookingSearvice {

    @Autowired
    ShowsDao showsDao;

    @Autowired
    SeatLockService seatLockService;

    @Autowired
    UserSeatMapDao userSeatMapDao;

    private final Logger logger = LoggerFactory.getLogger(SeatBookingServiceImpl.class);

    public SeatBookingStatusEnum lockSeats(long showId, List<String> seatIds, long userId) throws JsonProcessingException {
        Optional<ShowEntity> showEntity = showsDao.findById(showId);
        ObjectMapper objectMapper = new ObjectMapper();

        if (showEntity.isEmpty()) {
            return SeatBookingStatusEnum.SHOW_NOT_AVAILABLE;
        }

        Set<String> bookedSeats = new HashSet<>(objectMapper.convertValue(objectMapper.readTree(showEntity.get().getBookedSeats()), new TypeReference<>() {}));

        boolean isSeatAvailable = seatIds.stream().anyMatch(bookedSeats::contains);

        if (isSeatAvailable) {
            return SeatBookingStatusEnum.ALREADY_BOOKED;
        }

        boolean isSeatNotAlreadyLocked = seatIds.stream()
             .map(seatId -> seatLockService.lockSeat(showId, seatId, userId))
             .reduce(true, (result, acc) -> acc && result);

        if (!isSeatNotAlreadyLocked) return SeatBookingStatusEnum.ALREADY_LOCKED;

        return SeatBookingStatusEnum.LOCKED;
    }

    @Override
    public SeatBookingStatusEnum bookSeats(long showId, List<String> seatIds, long userId) throws JsonProcessingException, SeatBookingException {
        Optional<ShowEntity> showEntity = showsDao.findById(showId);
        ObjectMapper objectMapper = new ObjectMapper();

        if (showEntity.isEmpty()) {
            return SeatBookingStatusEnum.SHOW_NOT_AVAILABLE;
        }

        ShowEntity show = showEntity.get();

        List<String> bookedSeats = objectMapper.convertValue(objectMapper.readTree(show.getBookedSeats()), new TypeReference<>() {});

        Set<String> bookedSeatSet = new HashSet<>(bookedSeats);
        boolean isSeatAlreadyAvailable = seatIds.stream().anyMatch(bookedSeatSet::contains);
        boolean isSameUserLockedAllSeats = true;
        boolean isSeatsLocked = true;

        for (String seatId : seatIds) {
            boolean isCurrentSeatLocked = seatLockService.isSeatLocked(showId, seatId);
            if (isCurrentSeatLocked) {
                long lockingUserId = seatLockService.getUserIdByKey(showId, seatId);
                logger.info("User id from redis {}", String.valueOf(lockingUserId));
                if (userId != lockingUserId) {
                    isSameUserLockedAllSeats = false;
                }
            } else {
                isSeatsLocked = false;
            }
        }

        if (isSeatAlreadyAvailable || !isSeatsLocked || !isSameUserLockedAllSeats) {
            throw new SeatBookingException("Seat already booked");
        }

        bookedSeats.addAll(seatIds);
        String updatedBookedSeats = objectMapper.writeValueAsString(bookedSeats);
        show.setBookedSeats(updatedBookedSeats);
        showsDao.save(show);

        for (String seatId : seatIds) {
            seatLockService.releaseSeat(showId, seatId);
        }

        List<UserSeatMapEntity> userSeatMapEntityList = new ArrayList<>();
        for (String seatId : seatIds) {
            UserSeatMapEntity userSeatMapEntity = new UserSeatMapEntity.UserSeatMapEntityBuilder()
                    .show(show)
                    .userShowSeatId(new UserShowSeatId(showId, seatId, userId))
                    .build();
            userSeatMapEntityList.add(userSeatMapEntity);
        }

        userSeatMapDao.saveAll(userSeatMapEntityList);

        return SeatBookingStatusEnum.BOOKED;
    }
}
