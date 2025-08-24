package com.atcorp.eventmanagement.dto.event;

import com.atcorp.eventmanagement.util.enums.CategoryEnum;
import com.vladmihalcea.hibernate.type.range.Range;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {
    @NotNull
    private String eventName;
    private String description;
    @NotBlank
    private long venueId;
    @NotNull
    private CategoryEnum category;
    @NotNull
    private Range<LocalDateTime> eventTime;
}
