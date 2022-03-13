package com.hype.eventservice.api.event.dto;

import com.hype.eventservice.api.event.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private String name;
    private String artist;
    private String photo;
    private String description;
    private String city;
    private String location;
    private String link;
    private ZonedDateTime dateTime;

    public EventDTO(Event event){
        this.name = event.getName();
        this.artist = event.getArtist();
        this.photo = event.getPhoto();
        this.description = event.getDescription();
        this.city = event.getCity();
        this.location = event.getLocation();
        this.link = event.getLink();
        this.dateTime = event.getDateTime();
    }

}
