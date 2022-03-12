package com.hype.eventservice.api.event;

import com.hype.eventservice.api.event.dto.EventDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/events")
public class EventController {

    private final EventService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findEvents() {
        final var response = service.findAllEvents();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllArtists(@PathVariable BigInteger eventId) {
        final var response = service.findEventById(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createEvent(@RequestBody EventDTO eventDTO) {
        final var response = service.createEvent(eventDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateEvent(@RequestBody EventDTO eventDTO) {
        final var response = service.updateEvent(eventDTO);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteEvent(@PathVariable EventDTO eventDTO) {
        final var response = service.deleteEvent(eventDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}