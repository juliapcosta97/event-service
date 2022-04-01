package com.hype.eventservice.api.backoffice.common.domain;

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
@Table(name = "action")
@AllArgsConstructor
@NoArgsConstructor
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private BigInteger id;

    @Column(nullable = false)
    private String action;

    @Column(nullable = true)
    private ZonedDateTime dateCreated;

    @PrePersist
    public void preCreate() {
        ZonedDateTime now = ZonedDateTime.now();
        this.setDateCreated(now);
    }

    public Action(String action) {
        this.action = action;
    }
}
