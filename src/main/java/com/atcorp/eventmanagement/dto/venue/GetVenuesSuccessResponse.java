package com.atcorp.eventmanagement.dto.venue;

import com.atcorp.eventmanagement.dto.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetVenuesSuccessResponse implements SuccessResponse {
    private List<Venue> venues;
}
