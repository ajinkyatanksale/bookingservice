package com.atcorp.eventmanagement.dto.event;

import com.atcorp.eventmanagement.util.enums.CategoryEnum;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private long eventId;
    private String eventName;
    private String description;
    private long venueId;
    private CategoryEnum category;
    private Range<LocalDateTime> eventTime;
}
