package com.hype.eventservice.api.event.domain;

import com.hype.eventservice.api.event.dto.EventDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(nullable = true, length = 50)
    private String name;

    @Column(nullable = true, length = 50)
    private String artist;

    @Column(nullable = true)
    private String photo;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true, length = 50)
    private String city;

    @Column(nullable = true, length = 50)
    private String location;

    @Column(nullable = true)
    private ZonedDateTime dateCreated;

    public Event(EventDTO event) {
        this.id = event.getId();
        this.name = event.getName();
        this.artist = event.getArtist();
        this.photo = event.getPhoto();
        this.description = event.getDescription();
        this.city = event.getCity();
        this.location = event.getLocation();
    }

    @PrePersist
    public void preCreate() {
        ZonedDateTime now = ZonedDateTime.now();
        this.setDateCreated(now);
    }
}