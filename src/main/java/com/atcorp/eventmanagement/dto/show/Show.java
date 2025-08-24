package com.atcorp.eventmanagement.dto.show;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Show {
    private long showId;
    private long eventId;
    private Range<LocalDateTime> showTime;
    private String status;
    private JsonNode bookedSeats;
}
