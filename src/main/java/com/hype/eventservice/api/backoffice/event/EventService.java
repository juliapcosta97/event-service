package com.hype.eventservice.api.backoffice.event;


import com.hype.eventservice.api.backoffice.common.domain.Action;
import com.hype.eventservice.api.backoffice.common.domain.ActionRepository;
import com.hype.eventservice.api.backoffice.event.domain.Event;
import com.hype.eventservice.api.backoffice.event.domain.EventRepository;
import com.hype.eventservice.api.backoffice.event.dto.EventRequestDTO;
import com.hype.eventservice.api.backoffice.event.dto.EventResponseDTO;
import com.hype.eventservice.api.backoffice.common.dto.RestResponseDTO;
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

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private final EventRepository repository;
    private final ActionRepository actionRepository;

    public List<EventResponseDTO> findAllEvents(String sortValue, String sortBy, int sizeList) {
        var pageable = buildPageFilter(sortValue,sortBy,sizeList);
        var events = repository.findAll(pageable);

        actionRepository.save(new Action(GET_LIST));

        return events.stream().map(event -> new EventResponseDTO(event)).collect(Collectors.toList());
    }

    public EventResponseDTO findEventById(BigInteger eventId) {
        var event = repository.findById(eventId);
        var action = new Action(GET_BY_ID);
        actionRepository.save(action);

        return new EventResponseDTO(event.get());
    }

    public RestResponseDTO createEvent(EventRequestDTO eventDTO) {
        var event = new Event(eventDTO);
        repository.save(event);

        return new RestResponseDTO(CREATE_STATUS, CREATE_MESSAGE);
    }

    public RestResponseDTO updateEvent(EventRequestDTO eventDTO) {
        var event = new Event(eventDTO);

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
    }

    public RestResponseDTO deleteEvent(BigInteger eventId) {
        var event = repository.findById(eventId);
        repository.delete(event.get());

        return new RestResponseDTO(DELETE_STATUS, DELETE_MESSAGE);
    }

    private Pageable buildPageFilter(String sortValue, String sortBy, int sizeList) {
        Sort sort = Sort.by(sortValue).ascending();

        if(sortBy.equalsIgnoreCase("desc")){
            sort = Sort.by(sortValue).descending();
        }

        return PageRequest.of(0,sizeList, sort);
    }
}
