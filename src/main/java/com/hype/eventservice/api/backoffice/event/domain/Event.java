package com.hype.eventservice.api.backoffice.event.domain;

import com.hype.eventservice.api.backoffice.artist.domain.Artist;
import com.hype.eventservice.api.backoffice.event.dto.EventRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "event")
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigInteger id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String artist;

    @ManyToMany
    private List<Artist> artists;

    @Column
    private String photo;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String location;

    @Column
    private String link;

    @Column
    private String genre;

    @Column
    private String type;

    @Column
    private String reference;

    @Column
    private ZonedDateTime dateTime;

    @Column(nullable = false)
    private ZonedDateTime dateCreated;

    @Column(nullable = false)
    private ZonedDateTime dateUpdated;

    @PrePersist
    public void preCreate() {
        ZonedDateTime now = ZonedDateTime.now();
        this.setDateCreated(now);
    }

    public Event(EventRequestDTO event) {
        this.name = event.getName();
        this.artist = event.getArtist();
        this.photo = event.getPhoto();
        this.description = event.getDescription();
        this.city = event.getCity();
        this.location = event.getLocation();
        this.link = event.getLink();
        this.dateTime = event.getDateTime();
        this.dateUpdated = ZonedDateTime.now();
        this.genre = event.getGenre();
        this.type = event.getType();
        this.reference = event.getReference();
        this.artists = event.getArtists();
    }
}
