package com.atcorp.eventmanagement.dto.show;

import com.atcorp.eventmanagement.dto.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GetShowsSuccessResponse implements SuccessResponse {
    private List<Show> shows;
}
