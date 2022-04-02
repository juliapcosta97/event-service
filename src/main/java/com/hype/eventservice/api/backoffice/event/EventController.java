package com.hype.eventservice.api.backoffice.event;

import com.hype.eventservice.api.backoffice.common.dto.RestResponseDTO;
import com.hype.eventservice.api.backoffice.event.dto.EventRequestDTO;
import com.hype.eventservice.api.backoffice.event.dto.EventResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/events")
public class EventController {

    private final EventService service;

//    @Cacheable("events")
    @CacheEvict("events")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EventResponseDTO>> findAllEvents(
            @RequestParam("value") String sortValue,
            @RequestParam("sort_by") String sortBy,
            @RequestParam("size") int sizeList) {
        final var response = service.findAllEvents(sortValue,sortBy,sizeList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @Cacheable("events")
    @CacheEvict("events")
    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EventResponseDTO> findEventById(@PathVariable BigInteger eventId) {
        final var response = service.findEventById(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CacheEvict("events")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestResponseDTO> createEvent(@RequestBody EventRequestDTO eventDTO) {
        final var response = service.createEvent(eventDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CacheEvict("events")
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<RestResponseDTO> updateEvent(@RequestBody EventRequestDTO eventDTO) {
        final var response = service.updateEvent(eventDTO);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @CacheEvict("events")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestResponseDTO> deleteEvent(@RequestBody BigInteger eventId) {
        final var response = service.deleteEvent(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
