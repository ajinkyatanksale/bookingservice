package com.atcorp.eventmanagement.model;

import com.atcorp.eventmanagement.util.enums.CategoryEnum;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
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
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public static final class EventsBuilder {
        private long eventId;
        private String eventName;
        private String description;
        private long venueId;
        private CategoryEnum category;
        private Range<LocalDateTime> eventTime;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public EventsBuilder() {
        }

        public EventsBuilder(Event other) {
            this.eventId = other.eventId;
            this.eventName = other.eventName;
            this.description = other.description;
            this.venueId = other.venueId;
            this.category = other.category;
            this.eventTime = other.eventTime;
            this.createdAt = other.createdAt;
            this.updatedAt = other.updatedAt;
        }

        public static EventsBuilder anEvents() {
            return new EventsBuilder();
        }

        public EventsBuilder eventId(long eventId) {
            this.eventId = eventId;
            return this;
        }

        public EventsBuilder eventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public EventsBuilder description(String description) {
            this.description = description;
            return this;
        }

        public EventsBuilder category(CategoryEnum category) {
            this.category = category;
            return this;
        }

        public EventsBuilder venueId(long venueId) {
            this.venueId = venueId;
            return this;
        }

        public EventsBuilder eventTime(Range<LocalDateTime> eventTime) {
            this.eventTime = eventTime;
            return this;
        }

        public EventsBuilder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EventsBuilder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Event build() {
            Event events = new Event();
            events.setEventId(eventId);
            events.setEventName(eventName);
            events.setDescription(description);
            events.setVenueId(venueId);
            events.setCategory(category);
            events.setEventTime(eventTime);
            events.setCreatedAt(createdAt);
            events.setUpdatedAt(updatedAt);
            return events;
        }
    }
}
