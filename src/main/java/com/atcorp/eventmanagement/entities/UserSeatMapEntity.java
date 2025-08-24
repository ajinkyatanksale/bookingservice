package com.atcorp.eventmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="user_seat_map", schema="event_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSeatMapEntity {
    @EmbeddedId
    private UserShowSeatId userShowSeatId;

    @MapsId("showId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    private ShowEntity show;

    @Column(name="created_at")
    private Timestamp createdAt;
    @Column(name="updated_at")
    private Timestamp updatedAt;

    public static final class UserSeatMapEntityBuilder {
        private UserShowSeatId userShowSeatId;
        private ShowEntity show;

        public UserSeatMapEntityBuilder() {
        }

        public static UserSeatMapEntityBuilder anUserSeatMapEntity() {
            return new UserSeatMapEntityBuilder();
        }

        public UserSeatMapEntityBuilder userShowSeatId(UserShowSeatId userShowSeatId) {
            this.userShowSeatId = userShowSeatId;
            return this;
        }

        public UserSeatMapEntityBuilder show(ShowEntity show) {
            this.show = show;
            return this;
        }

        public UserSeatMapEntity build() {
            UserSeatMapEntity userSeatMapEntity = new UserSeatMapEntity();
            userSeatMapEntity.setUserShowSeatId(userShowSeatId);
            userSeatMapEntity.setShow(show);
            return userSeatMapEntity;
        }
    }
}
