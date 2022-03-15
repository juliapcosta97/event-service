package com.hype.eventservice.api.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private BigInteger id;
    private String name;
    private String artist;
    private String photo;
    private String description;
    private String city;
    private String location;
    private String link;
}
