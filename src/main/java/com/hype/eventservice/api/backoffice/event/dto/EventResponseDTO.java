package com.hype.eventservice.api.backoffice.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hype.eventservice.api.backoffice.artist.domain.Artist;
import com.hype.eventservice.api.backoffice.event.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO extends EventDTO {

    @JsonFormat(pattern="dd/MM/yyyy")
    private ZonedDateTime date;

    @JsonFormat(pattern="HH:mm:ss")
    private ZonedDateTime hour;

    private List<Artist> artists;

    public EventResponseDTO(Event event){
        super.setId(event.getId());
        super.setName(event.getName());
        super.setArtist(event.getArtist());
        super.setPhoto(event.getPhoto());
        super.setDescription(event.getDescription());
        super.setCity(event.getCity());
        super.setLocation(event.getLocation());
        super.setLink(event.getLink());
        this.setDate(event.getDateTime());
        this.setHour(event.getDateTime());
        this.setArtists(event.getArtists());
    }
}
