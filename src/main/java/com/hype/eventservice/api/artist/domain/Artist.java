package com.hype.eventservice.api.artist.domain;

import com.hype.eventservice.api.artist.dto.ArtistDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "artist")
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    @Id
    private BigInteger id;
    private String name;
    private String description;
    private String photo;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;

    public Artist(ArtistDTO artistDTO) {
        this.id = artistDTO.getId();
        this.name = artistDTO.getName();
        this.description = artistDTO.getDescription();
        this.dateCreated = ZonedDateTime.now();
        this.dateUpdated = ZonedDateTime.now();
    }
}
