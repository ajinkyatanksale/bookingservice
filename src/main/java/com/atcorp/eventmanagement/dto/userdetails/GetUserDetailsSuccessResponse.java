package com.atcorp.eventmanagement.dto.userdetails;

import com.atcorp.eventmanagement.dto.SuccessResponse;
import com.atcorp.eventmanagement.model.UserBookingsDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDetailsSuccessResponse implements SuccessResponse {
    private long userId;
    private List<UserBookingsDetails.Events> eventsList;
}
