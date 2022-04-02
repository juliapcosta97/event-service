package com.hype.eventservice.api.backoffice.interaction.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface InteractionRepository extends JpaRepository<Interaction, BigInteger> {
}
