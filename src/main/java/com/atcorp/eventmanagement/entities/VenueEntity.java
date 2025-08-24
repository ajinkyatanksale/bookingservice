package com.atcorp.eventmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "Venue", schema = "event_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenueEntity {
    @Id
    @Column(name="venue_id")
    @SequenceGenerator(name="venue_id_seq", sequenceName="event_management.venue_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "venue_id_seq")
    long venueId;
    @Column(name="venue_name")
    String venueName;
    @Column(name="city")
    String city;
    @Column(name="sections_json")
    String sectionsJson;
    @Column(name="pincode")
    String pinCode;
    @Column(name="created_at")
    Timestamp createdAt;
    @Column(name="updated_at")
    Timestamp updatedAt;


    public static final class VenueEntityBuilder {
        private String venueName;
        private String city;
        private String sectionsJson;
        private String pinCode;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public VenueEntityBuilder() {
        }

        public VenueEntityBuilder(VenueEntity other) {
            this.venueName = other.venueName;
            this.city = other.city;
            this.sectionsJson = other.sectionsJson;
            this.pinCode = other.pinCode;
            this.createdAt = other.createdAt;
            this.updatedAt = other.updatedAt;
        }

        public static VenueEntityBuilder aVenueEntity() {
            return new VenueEntityBuilder();
        }

        public VenueEntityBuilder venueName(String venueName) {
            this.venueName = venueName;
            return this;
        }

        public VenueEntityBuilder city(String city) {
            this.city = city;
            return this;
        }

        public VenueEntityBuilder sectionsJson(String sectionsJson) {
            this.sectionsJson = sectionsJson;
            return this;
        }

        public VenueEntityBuilder pinCode(String pinCode) {
            this.pinCode = pinCode;
            return this;
        }

        public VenueEntityBuilder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public VenueEntityBuilder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public VenueEntity build() {
            VenueEntity venueEntity = new VenueEntity();
            venueEntity.setVenueName(venueName);
            venueEntity.setCity(city);
            venueEntity.setSectionsJson(sectionsJson);
            venueEntity.setPinCode(pinCode);
            venueEntity.setCreatedAt(createdAt);
            venueEntity.setUpdatedAt(updatedAt);
            return venueEntity;
        }
    }
}
