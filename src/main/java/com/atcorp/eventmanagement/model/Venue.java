package com.atcorp.eventmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    long venueId;
    String venueName;
    String city;
    String sectionsJson;
    String pinCode;
    Timestamp createdAt;
    Timestamp updatedAt;


    public static final class VenuesBuilder {
        private long venueId;
        private String venueName;
        private String city;
        private String sectionsJson;
        private String pinCode;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public VenuesBuilder() {
        }

        public VenuesBuilder(Venue other) {
            this.venueId = other.venueId;
            this.venueName = other.venueName;
            this.city = other.city;
            this.sectionsJson = other.sectionsJson;
            this.pinCode = other.pinCode;
            this.createdAt = other.createdAt;
            this.updatedAt = other.updatedAt;
        }

        public static VenuesBuilder aVenues() {
            return new VenuesBuilder();
        }

        public VenuesBuilder venue_id(long venue_id) {
            this.venueId = venue_id;
            return this;
        }

        public VenuesBuilder venueName(String venueName) {
            this.venueName = venueName;
            return this;
        }

        public VenuesBuilder city(String city) {
            this.city = city;
            return this;
        }

        public VenuesBuilder sectionsJson(String sectionsJson) {
            this.sectionsJson = sectionsJson;
            return this;
        }

        public VenuesBuilder pinCode(String pinCode) {
            this.pinCode = pinCode;
            return this;
        }

        public VenuesBuilder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public VenuesBuilder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Venue build() {
            Venue venue = new Venue();
            venue.setVenueId(venueId);
            venue.setVenueName(venueName);
            venue.setCity(city);
            venue.setSectionsJson(sectionsJson);
            venue.setPinCode(pinCode);
            venue.setCreatedAt(createdAt);
            venue.setUpdatedAt(updatedAt);
            return venue;
        }
    }
}
