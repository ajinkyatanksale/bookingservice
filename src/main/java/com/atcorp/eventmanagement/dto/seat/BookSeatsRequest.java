package com.atcorp.eventmanagement.dto.seat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookSeatsRequest {
    @NotNull
    private long showId;
    @NotNull
    @NotEmpty
    private List<String> seats;
    @NotNull
    private long userId;
}
