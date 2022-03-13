package com.hype.eventservice.api.event;


import com.hype.eventservice.api.event.domain.Event;
import com.hype.eventservice.api.event.domain.EventRepository;
import com.hype.eventservice.api.event.dto.EventDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private final EventRepository repository;

    public List<EventDTO> findAllEvents() {
        final var events = repository.findAll();

        repository.resetConnect();

        return events.stream().map(event -> new EventDTO(event))
                .collect(Collectors.toList());
    }

    public EventDTO findEventById(BigInteger eventId) {
        var event = repository.findById(eventId);

        repository.resetConnect();

        if (event.isPresent()) {
            return new EventDTO(event.get());
        }

        throw new RuntimeException("Event not found");
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        final var event = new Event(eventDTO);

        repository.save(event);
        repository.resetConnect();

        return eventDTO;
    }

    public EventDTO updateEvent(EventDTO eventDTO) {
        final var event = new Event(eventDTO);

        repository.saveAndFlush(event);
        repository.resetConnect();

        return eventDTO;
    }

    public EventDTO deleteEvent(EventDTO eventDTO) {
        final var event = new Event(eventDTO);

        repository.delete(event);
        repository.resetConnect();

        return eventDTO;
    }
}
