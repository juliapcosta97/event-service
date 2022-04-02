package com.hype.eventservice.api.backoffice.artist.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ArtistRepository extends JpaRepository<Artist, BigInteger> {
}
