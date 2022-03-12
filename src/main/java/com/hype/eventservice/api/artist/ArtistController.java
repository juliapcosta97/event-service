package com.hype.eventservice.api.artist;

import com.hype.eventservice.api.artist.dto.ArtistDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/artist")
public class ArtistController {

    private final ArtistService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllArtists() {
        final var response = service.findAllArtists();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{artistId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllArtists(@PathVariable BigInteger artistId) {
        final var response = service.findArtistById(artistId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createEvent(@RequestBody ArtistDTO artistDTO) {
        final var response = service.saveArtist(artistDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateEvent(@RequestBody ArtistDTO artistDTO) {
        final var response = service.saveArtist(artistDTO);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteEvent(@PathVariable ArtistDTO artistDTO) {
        final var response = service.deleteArtist(artistDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
