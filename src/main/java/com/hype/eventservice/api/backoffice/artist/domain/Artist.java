package com.hype.eventservice.api.backoffice.artist.domain;

import com.hype.eventservice.api.backoffice.artist.dto.ArtistDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.PreUpdate;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "artist")
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigInteger id;

    @Column(nullable = false)
    private String name;

    @Column
    private String photo;

    @Column
    private String description;

    @Column(nullable = false)
    private ZonedDateTime dateCreated;

    @Column(nullable = false)
    private ZonedDateTime dateUpdated;

    @PrePersist
    public void preCreate() {
        ZonedDateTime now = ZonedDateTime.now();
        this.setDateCreated(now);
    }

    @PreUpdate
    public void preUpdate() {
        ZonedDateTime now = ZonedDateTime.now();
        this.setDateUpdated(now);
    }

    public Artist(ArtistDTO artist) {
        this.name = artist.getName();
        this.photo = artist.getPhoto();
        this.description = artist.getDescription();
        this.dateUpdated = ZonedDateTime.now();
    }
}
