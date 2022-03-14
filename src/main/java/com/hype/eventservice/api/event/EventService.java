package com.hype.eventservice.api.event;


import com.hype.eventservice.api.action.domain.Action;
import com.hype.eventservice.api.action.domain.ActionRepository;
import com.hype.eventservice.api.event.domain.Event;
import com.hype.eventservice.api.event.domain.EventRepository;
import com.hype.eventservice.api.event.dto.EventDTO;
import com.hype.eventservice.api.event.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.hype.eventservice.api.util.MessageUtils.CREATE_MESSAGE;
import static com.hype.eventservice.api.util.MessageUtils.CREATE_STATUS;
import static com.hype.eventservice.api.util.MessageUtils.UPDATE_MESSAGE;
import static com.hype.eventservice.api.util.MessageUtils.UPDATE_STATUS;
import static com.hype.eventservice.api.util.MessageUtils.DELETE_MESSAGE;
import static com.hype.eventservice.api.util.MessageUtils.DELETE_STATUS;
import static com.hype.eventservice.api.util.MessageUtils.GET_LIST;
import static com.hype.eventservice.api.util.MessageUtils.GET_BY_ID;
import static com.hype.eventservice.api.util.MessageUtils.ERROR_MESSAGE;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private final EventRepository repository;
    private final ActionRepository actionRepository;

    public List<EventDTO> findAllEvents() {
        try {
            var events = repository.findAll();
            actionRepository.save(new Action(GET_LIST));
            repository.resetConnect();

            return events.stream().map(event -> new EventDTO(event))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }

    public EventDTO findEventById(BigInteger eventId) {
        try {
            var event = repository.findById(eventId);
            var action = new Action(GET_BY_ID);
            actionRepository.save(action);
            repository.resetConnect();

            return new EventDTO(event.get());
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }

    public ResponseDTO createEvent(EventDTO eventDTO) {
        try {
            var event = new Event(eventDTO);
            repository.save(event);
            repository.resetConnect();

            return new ResponseDTO(CREATE_STATUS, CREATE_MESSAGE);
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }

    public ResponseDTO updateEvent(EventDTO eventDTO) {
        try {
            var event = new Event(eventDTO);
            repository.saveAndFlush(event);
            repository.resetConnect();

            return new ResponseDTO(UPDATE_STATUS, UPDATE_MESSAGE);
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }

    public ResponseDTO deleteEvent(EventDTO eventDTO) {
        try {
            var event = new Event(eventDTO);
            repository.delete(event);
            repository.resetConnect();

            return new ResponseDTO(DELETE_STATUS, DELETE_MESSAGE);
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }
}
