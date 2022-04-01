package com.hype.eventservice.api.backoffice.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO extends EventDTO {

    @JsonProperty(value = "date_time")
    private ZonedDateTime dateTime;
}
