package com.atcorp.eventmanagement.dto.venue;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    long venueId;
    String venueName;
    String city;
    JsonNode sectionsJson;
    String pinCode;
}
