package com.hype.eventservice.api.backoffice.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Column
    private ZonedDateTime dateCreated;

    @PrePersist
    public void preCreate() {
        ZonedDateTime now = ZonedDateTime.now();
        this.setDateCreated(now);
    }

    public User(String userIdentity) {
        this.id = userIdentity;
    }
}
