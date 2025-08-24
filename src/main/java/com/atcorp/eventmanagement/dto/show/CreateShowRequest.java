package com.atcorp.eventmanagement.dto.show;

import com.vladmihalcea.hibernate.type.range.Range;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateShowRequest {
    @NotNull
    private long eventId;
    @NotNull
    private Range<LocalDateTime> showTime;
    @NotNull
    private String status;
}
