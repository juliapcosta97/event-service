package com.hype.eventservice.api.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO extends EventDTO {

    private ZonedDateTime dateTime;
}
