package com.atcorp.eventmanagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SeatLockService {

    private static final long LOCK_DURATION = 300; // 5 min in seconds
    private final RedisTemplate<String, Object> redisTemplate;
    private final Logger logger = LoggerFactory.getLogger(SeatLockService.class);

    public SeatLockService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public synchronized boolean lockSeat(Long showId, String seatId, Long userId) {
        String key = getKey(showId, seatId);
        return Boolean.TRUE.equals(redisTemplate.opsForValue()
                .setIfAbsent(key, userId, LOCK_DURATION, TimeUnit.SECONDS));
    }

    public boolean isSeatLocked(Long showId, String seatId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(getKey(showId, seatId)));
    }

    public Long getUserIdByKey(Long showId, String seatId) {
        String key = getKey(showId, seatId);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return Long.parseLong(String.valueOf(valueOperations.get(key)));
    }

    public synchronized void releaseSeat(Long showId, String seatId) {
        redisTemplate.delete(getKey(showId, seatId));
    }

    private String getKey(Long showId, String seatId) {
        return "seat_lock:" + showId + ":" + seatId;
    }
}

