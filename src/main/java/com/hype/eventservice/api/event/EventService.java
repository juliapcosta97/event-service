package com.hype.eventservice.api.event;


import com.hype.eventservice.api.action.domain.Action;
import com.hype.eventservice.api.action.domain.ActionRepository;
import com.hype.eventservice.api.event.domain.Event;
import com.hype.eventservice.api.event.domain.EventRepository;
import com.hype.eventservice.api.event.dto.EventRequestDTO;
import com.hype.eventservice.api.event.dto.EventResponseDTO;
import com.hype.eventservice.api.event.dto.RestResponseDTO;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<EventResponseDTO> findAllEvents(String sortValue, String sortBy, int sizeList) {
        try {
            var pageable = buildPageFilter(sortValue,sortBy,sizeList);
            var events = repository.findAll(pageable);

            actionRepository.save(new Action(GET_LIST));
            repository.resetConnect();

            return events.stream().map(event -> new EventResponseDTO(event))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }

    private Pageable buildPageFilter(String sortValue, String sortBy, int sizeList) {
        Sort sort = Sort.by(sortValue).ascending();

        if(sortBy.equalsIgnoreCase("desc")){
            sort = Sort.by(sortValue).descending();
        }

        return PageRequest.of(0,sizeList, sort);
    }

    public EventResponseDTO findEventById(BigInteger eventId) {
        try {
            var event = repository.findById(eventId);
            var action = new Action(GET_BY_ID);
            actionRepository.save(action);
            repository.resetConnect();

            return new EventResponseDTO(event.get());
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }

    public RestResponseDTO createEvent(EventRequestDTO eventDTO) {
        try {
            var event = new Event(eventDTO);
            repository.save(event);
            repository.resetConnect();

            return new RestResponseDTO(CREATE_STATUS, CREATE_MESSAGE);
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }

    public RestResponseDTO updateEvent(EventRequestDTO eventDTO) {
        try {
            var event = new Event(eventDTO);
            repository.save(event);
            repository.resetConnect();

            var updateResponse = repository.findById(eventDTO.getId())
                    .map(record -> {
                        record.setName(eventDTO.getName());
                        record.setName(event.getName());
                        record.setArtist(event.getArtist());
                        record.setPhoto(event.getPhoto());
                        record.setDescription(event.getDescription());
                        record.setCity(event.getCity());
                        record.setLocation(event.getLocation());
                        record.setLink(event.getLink());
                        record.setDateTime(event.getDateTime());
                        repository.save(record);

                        return new RestResponseDTO(UPDATE_STATUS, UPDATE_MESSAGE);
                    });

            return updateResponse.get();
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }

    public RestResponseDTO deleteEvent(EventRequestDTO eventDTO) {
        try {
            var event = repository.findById(eventDTO.getId());
            repository.delete(event.get());
            repository.resetConnect();

            return new RestResponseDTO(DELETE_STATUS, DELETE_MESSAGE);
        } catch (Exception ex) {
            throw new RuntimeException(ERROR_MESSAGE, ex);
        }
    }
}
