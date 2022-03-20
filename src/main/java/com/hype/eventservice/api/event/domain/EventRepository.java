package com.hype.eventservice.api.event.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface EventRepository extends JpaRepository<Event, BigInteger> {
}
