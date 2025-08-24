package com.atcorp.eventmanagement.model;

import com.atcorp.eventmanagement.util.enums.ShowStatusEnum;
import com.fasterxml.jackson.databind.JsonNode;
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
public class Show {
    private long showId;
    private long eventId;
    private Range<LocalDateTime> showTime;
    private ShowStatusEnum status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private JsonNode bookedSeats;

    public static final class ShowsBuilder {
        private long showId;
        private long eventId;
        private Range<LocalDateTime> showTime;
        private ShowStatusEnum status;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private JsonNode bookedSeats;

        public ShowsBuilder() {
        }

        public ShowsBuilder(Show other) {
            this.showId = other.showId;
            this.eventId = other.eventId;
            this.showTime = other.showTime;
            this.status = other.status;
            this.createdAt = other.createdAt;
            this.updatedAt = other.updatedAt;
            this.bookedSeats = other.bookedSeats;
        }

        public static ShowsBuilder aShows() {
            return new ShowsBuilder();
        }

        public ShowsBuilder showId(long showId) {
            this.showId = showId;
            return this;
        }

        public ShowsBuilder eventId(long eventId) {
            this.eventId = eventId;
            return this;
        }

        public ShowsBuilder showTime(Range<LocalDateTime> showTime) {
            this.showTime = showTime;
            return this;
        }

        public ShowsBuilder status(ShowStatusEnum status) {
            this.status = status;
            return this;
        }

        public ShowsBuilder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ShowsBuilder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ShowsBuilder bookedSeats(JsonNode bookedSeats) {
            this.bookedSeats = bookedSeats;
            return this;
        }

        public Show build() {
            Show shows = new Show();
            shows.setShowId(showId);
            shows.setEventId(eventId);
            shows.setShowTime(showTime);
            shows.setStatus(status);
            shows.setCreatedAt(createdAt);
            shows.setUpdatedAt(updatedAt);
            shows.setBookedSeats(bookedSeats);
            return shows;
        }
    }
}
