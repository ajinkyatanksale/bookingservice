package com.atcorp.eventmanagement.dto.venue;

import com.atcorp.eventmanagement.annotations.ValidSeatMap;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVenueRequest {
    @NotNull
    String venueName;
    @NotNull
    String city;
    @NotNull
    @ValidSeatMap
    JsonNode sectionsJson;
    @NotNull
    @Size(min = 6, max = 6)
    String pinCode;
}
