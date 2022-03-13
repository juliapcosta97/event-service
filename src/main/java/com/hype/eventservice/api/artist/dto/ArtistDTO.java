package com.hype.eventservice.api.artist.dto;

import com.hype.eventservice.api.artist.domain.Artist;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ArtistDTO {

    private BigInteger id;

    private String name;

    private String description;

    private String photo;

    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.description = artist.getDescription();
        this.photo = artist.getDescription();
    }
}
