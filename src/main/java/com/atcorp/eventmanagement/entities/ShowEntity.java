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
@Table(name = "Show", schema = "event_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowEntity {
    @Id
    @Column(name="show_id")
    @SequenceGenerator(name="show_id_seq", sequenceName="event_management.show_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "show_id_seq")
    long showId;
    @ManyToOne
    @JoinColumn(name = "event_id")
    EventEntity eventEntity;
    @ManyToOne
    @JoinColumn(name="venue_id")
    VenueEntity venueEntity;
    @Column(name="show_time")
    private Range<LocalDateTime> showTime;
    @Column(name="status")
    String status;
    @Column(name="created_at")
    Timestamp createdAt;
    @Column(name="updated_at")
    Timestamp updatedAt;
    @Column(name="booked_seats")
    String bookedSeats;


    public static final class ShowEntityBuilder {
        private EventEntity eventEntity;
        private VenueEntity venueEntity;
        private Range<LocalDateTime> showTime;
        private String status;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private String bookedSeats;

        public ShowEntityBuilder() {
        }

        public ShowEntityBuilder(ShowEntity other) {
            this.eventEntity = other.eventEntity;
            this.venueEntity = other.venueEntity;
            this.showTime = other.showTime;
            this.status = other.status;
            this.createdAt = other.createdAt;
            this.updatedAt = other.updatedAt;
            this.bookedSeats = other.bookedSeats;
        }

        public static ShowEntityBuilder aShowEntity() {
            return new ShowEntityBuilder();
        }

        public ShowEntityBuilder eventEntity(EventEntity eventEntity) {
            this.eventEntity = eventEntity;
            return this;
        }

        public ShowEntityBuilder venueEntity(VenueEntity venueEntity) {
            this.venueEntity = venueEntity;
            return this;
        }

        public ShowEntityBuilder showTime(Range<LocalDateTime> showTime) {
            this.showTime = showTime;
            return this;
        }

        public ShowEntityBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ShowEntityBuilder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ShowEntityBuilder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ShowEntityBuilder bookedSeats(String bookedSeats) {
            this.bookedSeats = bookedSeats;
            return this;
        }

        public ShowEntity build() {
            ShowEntity showEntity = new ShowEntity();
            showEntity.setEventEntity(eventEntity);
            showEntity.setVenueEntity(venueEntity);
            showEntity.setShowTime(showTime);
            showEntity.setStatus(status);
            showEntity.setCreatedAt(createdAt);
            showEntity.setUpdatedAt(updatedAt);
            showEntity.setBookedSeats(bookedSeats);
            return showEntity;
        }
    }
}
