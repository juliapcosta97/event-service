package com.hype.eventservice.api.event.domain;

import com.hype.eventservice.api.event.dto.EventRequestDTO;
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
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private BigInteger id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = true)
    private String photo;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String location;

    @Column(nullable = true)
    private String link;

    @Column(nullable = true)
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
    }
}
