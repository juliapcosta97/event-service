package com.hype.eventservice.api.backoffice.event.dto;

import com.hype.eventservice.api.backoffice.artist.domain.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

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
    private String genre;
    private String type;
    private String reference;
}
