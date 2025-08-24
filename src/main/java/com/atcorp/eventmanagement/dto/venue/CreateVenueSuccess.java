package com.atcorp.eventmanagement.dto.venue;

import com.atcorp.eventmanagement.dto.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVenueSuccess implements SuccessResponse {
    String venueId;
    String message;
}
