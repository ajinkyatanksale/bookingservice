package com.atcorp.eventmanagement.model;

import com.atcorp.eventmanagement.util.enums.ShowStatusEnum;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBookingsDetails {
    private long userId;
    private List<Events> eventsList;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Events extends Event {
        private Venue venue;
        private List<Show> showList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Show {
        private long showId;
        private Range<LocalDateTime> showTime;
        private ShowStatusEnum showStatusEnum;
        private List<String> bookedSeats;
    }

    public static final class UserBookingsDetailsBuilder {
        private long userId;
        private List<Events> eventsList;

        public UserBookingsDetailsBuilder() {
        }

        public UserBookingsDetailsBuilder(UserBookingsDetails other) {
            this.userId = other.userId;
            this.eventsList = other.eventsList;
        }

        public static UserBookingsDetailsBuilder anUserData() {
            return new UserBookingsDetailsBuilder();
        }

        public UserBookingsDetailsBuilder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public UserBookingsDetailsBuilder eventsList(List<Events> eventsList) {
            this.eventsList = eventsList;
            return this;
        }

        public UserBookingsDetails build() {
            UserBookingsDetails userBookingsDetails = new UserBookingsDetails();
            userBookingsDetails.setUserId(userId);
            userBookingsDetails.setEventsList(eventsList);
            return userBookingsDetails;
        }
    }
}

