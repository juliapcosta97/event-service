package com.hype.eventservice.api.backoffice.interaction.domain;

import com.hype.eventservice.api.backoffice.common.domain.User;
import com.hype.eventservice.api.backoffice.event.domain.Event;
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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "interaction")
@AllArgsConstructor
@NoArgsConstructor
public class Interaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name="userId",referencedColumnName="id")
    public User user;

    @ManyToOne
    @JoinColumn(name="eventId",referencedColumnName="id")
    public Event event;

    @Column(nullable = false)
    private String action;

    @Column
    private ZonedDateTime dateCreated;

    @PrePersist
    public void preCreate() {
        ZonedDateTime now = ZonedDateTime.now();
        this.setDateCreated(now);
    }

    public Interaction(User user, Event event, String action) {
        this.user = user;
        this.event = event;
        this.action = action;
    }
}
