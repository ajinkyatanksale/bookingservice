package com.atcorp.eventmanagement.entities;

import com.vladmihalcea.hibernate.type.range.Range;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Event", schema = "event_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id
    @Column(name = "event_id")
    @SequenceGenerator(name="event_id_seq", sequenceName="event_management.event_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "event_id_seq")
    private long eventId;
    @Column(name="event_name")
    private String eventName;
    @Column(name="description")
    private String description;
    @Column(name="category")
    private String category;
    @ManyToOne
    @JoinColumn(name="venue_id")
    private VenueEntity venue;
    @Column(name="event_time")
    private Range<LocalDateTime> eventTime;
    @Column(name="created_at")
    private Timestamp createdAt;
    @Column(name="updated_at")
    private Timestamp updatedAt;

    public static final class EventEntityBuilder {
        private String eventName;
        private String description;
        private String category;
        private VenueEntity venue;
        private Range<LocalDateTime> eventTime;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public EventEntityBuilder() {
        }

        public EventEntityBuilder(EventEntity other) {
            this.eventName = other.eventName;
            this.description = other.description;
            this.category = other.category;
            this.venue = other.venue;
            this.eventTime = other.eventTime;
            this.createdAt = other.createdAt;
            this.updatedAt = other.updatedAt;
        }

        public static EventEntityBuilder anEventEntity() {
            return new EventEntityBuilder();
        }

        public EventEntityBuilder eventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public EventEntityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public EventEntityBuilder category(String category) {
            this.category = category;
            return this;
        }

        public EventEntityBuilder venue(VenueEntity venue) {
            this.venue = venue;
            return this;
        }

        public EventEntityBuilder eventTime(Range<LocalDateTime> eventTime) {
            this.eventTime = eventTime;
            return this;
        }

        public EventEntityBuilder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EventEntityBuilder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public EventEntity build() {
            EventEntity eventEntity = new EventEntity();
            eventEntity.setEventName(eventName);
            eventEntity.setDescription(description);
            eventEntity.setCategory(category);
            eventEntity.setVenue(venue);
            eventEntity.setEventTime(eventTime);
            eventEntity.setCreatedAt(createdAt);
            eventEntity.setUpdatedAt(updatedAt);
            return eventEntity;
        }
    }
}
