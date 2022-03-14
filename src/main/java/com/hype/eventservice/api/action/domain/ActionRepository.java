package com.hype.eventservice.api.action.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ActionRepository extends JpaRepository<Action, BigInteger> {
}
